package com.global.console.service.impl;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.global.console.configuration.ApiConfiguration;
import com.global.console.model.Plan;
import com.global.console.model.User;
import com.global.console.model.WebService;
import com.global.console.model.WebServiceRequests;
import com.global.console.repository.PlanRepository;
import com.global.console.repository.RequestRepository;
import com.global.console.repository.UserRepository;
import com.global.console.response.Response;
import com.global.console.service.AdminRequest;

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

	/** The plan repository. */
	@Autowired
	private PlanRepository planRepository;
	
	/* (non-Javadoc)
	 * @see com.global.console.service.AdminRequest#viewRequests()
	 */
	@Override
	public Response<WebServiceRequests> viewRequests() {
		Response<WebServiceRequests> response = new Response<>(requestRepository.findAll(), HttpStatus.OK, "Request Completed");
		return response;
	}

	/* (non-Javadoc)
	 * @see com.global.console.service.AdminRequest#grantService(java.lang.String)
	 */
	@Override
	public Response<String> grantService(String requestId) {

			Response<String> response;
			UUID id = UUID.fromString(requestId);
			WebServiceRequests webServiceRequest = requestRepository.findById(id);
			Plan plan = planRepository.findByPlanId(UUID.fromString(webServiceRequest.getPlanId()));
			if(plan==null)
			{
				response = new Response<>(HttpStatus.BAD_REQUEST, "No such plan exists");
			}
			else
			{
			webServiceRequest.setStatus("completed");
			requestRepository.save(webServiceRequest);
			Map<String, String> params = new HashMap<>();
			String url = null;
			try {
				params.put("name", "rate-limiting");
				params.put("consumer_id", webServiceRequest.getUserId());
				params.put("config." + plan.getConfigType(), ""+plan.getConfigQuantity());
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
			webService.setUrl(apiConfig.getUserUrl() + "/" + webService.getName());
			List<WebService> webServiceList = user.getWebServices();
			if (webServiceList == null) {
				webServiceList = new ArrayList<>();
			}
			webServiceList.add(webService);
			user.setWebServices(webServiceList);
			repository.save(user);
			response = new Response<>(Arrays.asList(id.toString()), HttpStatus.OK, "Request Completed");
			}
			return response;
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