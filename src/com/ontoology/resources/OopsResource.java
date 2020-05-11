package com.ontoology.resources;



import java.util.logging.Logger;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import com.ontoology.util.SSLUtils;

public class OopsResource {
	
    private static final Logger LOG = Logger.getLogger(OopsResource.class.getName());
  
    //OOPS API
	public static String getOopsReport(String ontology)  {
		
		String uri = "http://oops.linkeddata.es/rest";

		try {
			//NOT NECESARY IN PRODUCTION
			SSLUtils.turnOffSslChecking();
			
			RestTemplate rt = new RestTemplate();
			
			String s = "<?xml version='1.' encoding='UTF-8'?>"
			+"<OOPSRequest>"
			+"<OntologyURI></OntologyURI>"
			+"<OntologyContent>"+ontology+"</OntologyContent>"
			+"<Pitfalls></Pitfalls>"
			+"<OutputFormat>RDF/XML</OutputFormat>"
		+"</OOPSRequest>";

			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_XML);

			HttpEntity<String> entity = new HttpEntity<String>(s,headers);
			String answer = rt.postForObject(uri, entity, String.class);
			
			return answer;
			
		} catch (Exception e) {
			LOG.warning(e.getMessage());
			return "Oops! service is not available at this moment, please try again later";
		}
	}

}
