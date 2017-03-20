package com.global.console.service.impl;

import java.io.IOException;
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

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.global.console.dao.impl.UserDaoImpl;
import com.global.console.model.User;
import com.global.console.model.WebService;
import com.global.console.model.WebServiceRequests;
import com.global.console.repository.RequestRepository;
import com.global.console.repository.UserRepository;
import com.global.console.service.AdminRequest;

/**
 * The Class AdminServiceImpl.
 */
@Service
public class AdminRequestImpl implements AdminRequest {

	/** The Constant KONGADMIN. */
	private static final String KONGADMIN = "172.16.24.73:8001";

	/** The Constant KONGUSER. */
	private static final String KONGUSER = "172.16.24.73:8000";

	/** The repository. */
	@Autowired
	private UserRepository repository;

	/** The request repository. */
	@Autowired
	private RequestRepository requestRepository;

	@Autowired
	UserDaoImpl userDaoImpl;

	@Override
	public List<WebServiceRequests> viewRequests() {
		return requestRepository.findAll();
	}

	@SuppressWarnings("unchecked")
	@Override
	public String grantService(String inputParams) {

		Map<String, Object> inputs = null;
		try {
			inputs = getInputParamsClass(inputParams, Map.class);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		if (inputs.get("id") != null) {
			UUID id = UUID.fromString((String) inputs.get("id"));
			String configType = (inputs.get("configType") != null ? (String) inputs.get("configType") : "hour");
			String permittedRequests = (inputs.get("permittedRequests") != null
					? (String) inputs.get("permittedRequests") : "100");

			WebServiceRequests webServiceRequest = requestRepository.findById(id);
			webServiceRequest.setStatus("completed");
			requestRepository.save(webServiceRequest);
			Map<String, String> params = new HashMap<>();
			String url = null;
			try {
				params.put("name", "rate-limiting");
				params.put("consumer_id", webServiceRequest.getUserId());
				params.put("config." + configType, permittedRequests);
				url = "http://" + KONGADMIN + "/apis/" + webServiceRequest.getServiceName() + "/plugins/";
				postRequest(url, params, String.class);

				params.clear();
				params.put("group", webServiceRequest.getServiceName());
				url = "http://" + KONGADMIN + "/consumers/" + webServiceRequest.getUserId() + "/acls/";
				postRequest(url, params, String.class);

			} catch (URISyntaxException e) {
				e.printStackTrace();
			}

			User user = repository.findOne(webServiceRequest.getUserId());
			WebService webService = new WebService();
			webService.setName(webServiceRequest.getServiceName());
			// webService.setUrl("http://" + KONGUSER + "/" +
			// webService.getName() + "?apikey=" + user.getKey());
			webService.setUrl("http://" + KONGUSER + "/" + webService.getName());
			List<WebService> webServiceList = user.getWebServices();
			if (webServiceList == null) {
				webServiceList = new ArrayList<>();
			}
			webServiceList.add(webService);
			user.setWebServices(webServiceList);
			repository.save(user);
			return id.toString();
		} else {
			return null;
		}
	}

	/**
	 * Gets the input params class.
	 *
	 * @param <T>
	 *            the generic type
	 * @param inputParams
	 *            the input params
	 * @param t
	 *            the t
	 * @return the input params class
	 * @throws JsonParseException
	 *             the json parse exception
	 * @throws JsonMappingException
	 *             the json mapping exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	private <T> T getInputParamsClass(String inputParams, Class<T> t)
			throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.readValue(inputParams, t);
	}

	/**
	 * Gets the request.
	 *
	 * @param <T>
	 *            the generic type
	 * @param url
	 *            the url
	 * @param params
	 *            the params
	 * @param t
	 *            the t
	 * @return the request
	 * @throws URISyntaxException
	 */

	/**
	 * Post request.
	 *
	 * @param <T>
	 *            the generic type
	 * @param url
	 *            the url
	 * @param params
	 *            the params
	 * @param t
	 *            the t
	 * @return the t
	 * @throws URISyntaxException
	 *             the URI syntax exception
	 */
	private <T> T postRequest(String url, Map<String, String> params, Class<T> t) throws URISyntaxException {
		URI uri = new URI(url);
		RestTemplate restTemplate = new RestTemplate();
		return restTemplate.postForObject(uri, params, t);
	}

}