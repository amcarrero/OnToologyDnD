package com.ontoology.resources;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ontoology.model.ThemisApiPojo;

//THEMIS API
public class ThemisResource {

	public String executeTests(String ontology, String tests) throws Exception {

		String uri = "http://themis.linkeddata.es/rest/api/results";
		String[] ontologies = new String[1];
		ontologies[0] = ontology;
		ThemisApiPojo content = new ThemisApiPojo(ontologies, tests);
		RestTemplate rt = new RestTemplate();
		ObjectMapper om = new ObjectMapper();

		String requestJson = om.writeValueAsString(content);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<String> entity = new HttpEntity<String>(requestJson, headers);
		String answer = rt.postForObject(uri, entity, String.class);

		return answer;

	}

	public String getTests(String ontology) {

		String uri = "http://themis.linkeddata.es/rest/api/example";
		RestTemplate rt = new RestTemplate();
		String answer = rt.postForObject(uri, ontology, String.class);
		return answer;

	}

}
