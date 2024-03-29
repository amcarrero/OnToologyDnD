package main.java.com.ontoology.resources;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import com.fasterxml.jackson.databind.ObjectMapper;
import main.java.com.ontoology.model.AstreaApiPojo;
import main.java.com.ontoology.util.SSLUtils;

public class AstreaResource {

	// ASTREA API
	public String getShaclShapes(String ontology) throws Exception {

		String uri = "https://astrea.linkeddata.es/api/shacl/document";
		AstreaApiPojo content = new AstreaApiPojo(ontology, "RDF/XML");

		SSLUtils.turnOffSslChecking();
		ObjectMapper om = new ObjectMapper();

		String requestJson = om.writeValueAsString(content);

		URL url = new URL(uri);
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("POST");
		con.setRequestProperty("Content-Type", "application/json; utf-8");
		con.setDoOutput(true);
		OutputStream os = con.getOutputStream();
		byte[] input = requestJson.getBytes("utf-8");
		os.write(input, 0, input.length);

		BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"));
		StringBuilder response = new StringBuilder();
		String responseLine = null;
		while ((responseLine = br.readLine()) != null) {
			response.append(responseLine.trim() + "\n");
		}

		return response.toString();

	}

}
