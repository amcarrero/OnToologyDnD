package com.ontoology.resources;


import java.util.logging.Logger;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ontoology.model.ThemisApiPojo;

//THEMIS API
public class ThemisResource {
	
    private static final Logger LOG = Logger.getLogger(AstreaResource.class.getName());

	public String executeTests(String ontology, String tests)  {
		
		String uri = "http://themis.linkeddata.es/rest/api/results";
		String[] ontologies = new String[1];
		ontologies[0] = ontology;
		ThemisApiPojo content = new ThemisApiPojo(ontologies, tests);
		try {
			RestTemplate rt = new RestTemplate();
			ObjectMapper om = new ObjectMapper();

			String requestJson = om.writeValueAsString(content);
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);

			HttpEntity<String> entity = new HttpEntity<String>(requestJson,headers);
			String answer = rt.postForObject(uri, entity, String.class);
			
			return answer;
			
		} catch (Exception e) {
			LOG.warning(e.getMessage());
			return "Themis service is not available at this moment, please try again later";
		}
		
	}
	
public String getTests(String ontology)  {
		
		String uri = "http://themis.linkeddata.es/rest/api/example";
		try {
						
			RestTemplate rt = new RestTemplate();
					
			String answer = rt.postForObject(uri, ontology, String.class);

			return answer;
			
		} catch (Exception e) {
			LOG.warning(e.getMessage());
			return "Themis service is not available at this moment, please try again later";
		}
		
	}

}
