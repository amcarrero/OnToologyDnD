package com.ontoology.resources;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Logger;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
			ObjectMapper om = new ObjectMapper();

			String requestJson = om.writeValueAsString(content);
			
			URL url = new URL (uri);
			HttpURLConnection con = (HttpURLConnection)url.openConnection();
			con.setRequestMethod("POST");
			con.setRequestProperty("Content-Type", "application/json; utf-8");
			con.setDoOutput(true);
			OutputStream os = con.getOutputStream(); 
			byte[] input = requestJson.getBytes("utf-8");
			os.write(input, 0, input.length);
				
			BufferedReader br = new BufferedReader( new InputStreamReader(con.getInputStream(), "utf-8")); 
			StringBuilder response = new StringBuilder();
			String responseLine = null;
			while ((responseLine = br.readLine()) != null) {
					  response.append(responseLine.trim());
			}
					

			
			return response.toString();
			
		} catch (Exception e) {
			LOG.warning(e.getMessage());    
			return "Astrea service is not available at this moment, please try again later";
		}
		
	}

}
