package com.global.console.service.impl;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.global.console.configuration.ApiConfiguration;
import com.global.console.dao.impl.UserDaoImpl;
import com.global.console.model.User;
import com.global.console.model.WebService;
import com.global.console.model.WebServiceRequests;
import com.global.console.repository.RequestRepository;
import com.global.console.repository.UserRepository;
import com.global.console.service.AdminRequest;

// TODO: Auto-generated Javadoc
/**
 * The Class AdminServiceImpl.
 */
@Service
public class AdminRequestImpl implements AdminRequest {

	/** The api config. */
	@Autowired
	private ApiConfiguration apiConfig;

	/** The repository. */
	@Autowired
	private UserRepository repository;

	/** The request repository. */
	@Autowired
	private RequestRepository requestRepository;

	/** The user dao impl. */
	@Autowired
	UserDaoImpl userDaoImpl;

	/* (non-Javadoc)
	 * @see com.global.console.service.AdminRequest#viewRequests()
	 */
	@Override
	public List<WebServiceRequests> viewRequests() {
		return requestRepository.findAll();
	}

	/* (non-Javadoc)
	 * @see com.global.console.service.AdminRequest#grantService(java.lang.String)
	 */
	@Override
	public String grantService(String requestId) {

			UUID id = UUID.fromString(requestId);
			WebServiceRequests webServiceRequest = requestRepository.findById(id);
			String configType = (webServiceRequest.getSubscription().equalsIgnoreCase("free") ? "hour" : (webServiceRequest.getSubscription().equalsIgnoreCase("silver")) ? "minute" : "second");
			String permittedRequests = "10";

			webServiceRequest.setStatus("completed");
			requestRepository.save(webServiceRequest);
			Map<String, String> params = new HashMap<>();
			String url = null;
			try {
				params.put("name", "rate-limiting");
				params.put("consumer_id", webServiceRequest.getUserId());
				params.put("config." + configType, permittedRequests);
				url = apiConfig.getAdminUrl() + "/apis/" + webServiceRequest.getServiceName() + "/plugins/";
				postRequest(url, params, String.class);

				params.clear();
				params.put("group", webServiceRequest.getServiceName());
				url = apiConfig.getAdminUrl() + "/consumers/" + webServiceRequest.getUserId() + "/acls/";
				postRequest(url, params, String.class);

			} catch (URISyntaxException e) {
				e.printStackTrace();
			}

			User user = repository.findOne(webServiceRequest.getUserId());
			WebService webService = new WebService();
			webService.setName(webServiceRequest.getServiceName());
			// webService.setUrl("http://" + KONGUSER + "/" +
			// webService.getName() + "?apikey=" + user.getKey());
			webService.setUrl(apiConfig.getUserUrl() + "/" + webService.getName());
			List<WebService> webServiceList = user.getWebServices();
			if (webServiceList == null) {
				webServiceList = new ArrayList<>();
			}
			webServiceList.add(webService);
			user.setWebServices(webServiceList);
			repository.save(user);
			return id.toString();
	}


	/**
	 * Post request.
	 *
	 * @param <T> the generic type
	 * @param url the url
	 * @param params the params
	 * @param t the t
	 * @return the t
	 * @throws URISyntaxException the URI syntax exception
	 */
	private <T> T postRequest(String url, Map<String, String> params, Class<T> t) throws URISyntaxException {
		URI uri = new URI(url);
		RestTemplate restTemplate = new RestTemplate();
		return restTemplate.postForObject(uri, params, t);
	}

}