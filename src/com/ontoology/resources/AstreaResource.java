package com.ontoology.resources;

import java.util.logging.Logger;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ontoology.model.AstreaApiPojo;
import com.ontoology.util.SSLUtils;

public class AstreaResource {
	
    private static final Logger LOG = Logger.getLogger(AstreaResource.class.getName());

    //ASTREA API
	public String getShaclShapes(String ontology)  {
		
		String uri = "https://astrea.linkeddata.es/api/shacl/document";
		AstreaApiPojo content = new AstreaApiPojo(ontology, "RDF/XML");
		try {
			
			SSLUtils.turnOffSslChecking();
			RestTemplate rt = new RestTemplate();
			ObjectMapper om = new ObjectMapper();

			String requestJson = om.writeValueAsString(content);
			
			HttpHeaders headers = new HttpHeaders();
			
			headers.setContentType(MediaType.APPLICATION_JSON);			
			
			HttpEntity<String> entity = new HttpEntity<String>(requestJson ,headers);
			
			String answer = rt.postForObject(uri, entity, String.class);
						
			return answer;
			
		} catch (Exception e) {
			LOG.warning(e.getMessage());    
			return "Astrea service is not available at this moment, please try again later";
		}
		
	}

}
