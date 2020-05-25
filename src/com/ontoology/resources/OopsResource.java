package com.ontoology.resources;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import com.ontoology.util.SSLUtils;

public class OopsResource {

	// OOPS API
	public static String getOopsReport(String ontology) throws Exception {

		String uri = "http://oops.linkeddata.es/rest";

		// NOT NECESARY IN PRODUCTION
		SSLUtils.turnOffSslChecking();

		RestTemplate rt = new RestTemplate();

		String s = "<?xml version='1.' encoding='UTF-8'?>" + "<OOPSRequest>" + "<OntologyURI></OntologyURI>"
				+ "<OntologyContent>" + ontology + "</OntologyContent>" + "<Pitfalls></Pitfalls>"
				+ "<OutputFormat>RDF/XML</OutputFormat>" + "</OOPSRequest>";

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_XML);

		HttpEntity<String> entity = new HttpEntity<String>(s, headers);
		String answer = rt.postForObject(uri, entity, String.class);

		return answer;

	}

}
