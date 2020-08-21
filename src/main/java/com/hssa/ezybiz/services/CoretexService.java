package com.hssa.ezybiz.services;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CoretexService {

	@Value("${service.CoretexUserLogin}")
	private String coretexUserLoginURL;
	@Value("${service.CoretexUserID}")
	private String coretexUserID;
	@Value("${service.CoretexUserPassword}")
	private String coretexUserPassword;
	@Value("${service.Ocp-Apim-Subscription-Key}")
	private String ocpApimSubscriptionKey;

	@SuppressWarnings("unchecked")
	public String getCoretexSessionId() {
		// TODO Auto-generated method stub
		try {	
			String url = "https://apihub.coretex.com/logon/ny/18.2/Logon/UserLogin";

			// create an instance of RestTemplate
			RestTemplate restTemplate = new RestTemplate();

			// create headers
			HttpHeaders headers = new HttpHeaders();
			// set `content-type` header
			headers.setContentType(MediaType.APPLICATION_JSON);
			// set `accept` header
			headers.add("Ocp-Apim-Subscription-Key","647de6a77c27427d96d98ee0b7cf471b");
			headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

			// request body parameters
			Map<String, Object> map = new HashMap();

			       map.put("Username", "zAPILoginHC");
			       map.put("Password", "gbxz3739UF");

			HttpEntity<Map<String, Object>> entity = new HttpEntity<>(map, headers);

			ResponseEntity<Map<String,Object>> response = restTemplate.exchange(url, HttpMethod.POST,
					entity, new ParameterizedTypeReference<Map<String,Object>>() {
					});
			 LinkedHashMap<String, String> lhm = (LinkedHashMap<String, String>) response.getBody().get("Value");
			//System.out.println(lhm.get("SessionID"));		
			
			return lhm.get("SessionID");

         } catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

/*	public List<Material> getAllMaterialForQuotationByID(String quotationId) {
		// TODO Auto-generated method stub
		try {
			HttpHeaders headers = new HttpHeaders();

			// can set the content Type
			headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
			headers.setContentType(MediaType.APPLICATION_JSON);
			UriComponentsBuilder builder = UriComponentsBuilder
					.fromHttpUrl(pricegliderURL + "/rest/materialDetails/getAllMaterialForQuotationByID")
					.queryParam("quotationId", Integer.parseInt(quotationId));
			RestTemplate restTemplate = new RestTemplate();

			HttpEntity<String> entity = new HttpEntity<String>(headers);

			ResponseEntity<List<Material>> response = restTemplate.exchange(builder.toUriString(), HttpMethod.POST,
					entity, new ParameterizedTypeReference<List<Material>>() {
					});
			return response.getBody();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
*/
}
