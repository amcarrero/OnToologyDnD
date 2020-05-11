package com.ontoology.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.ontoology.resources.ThemisResource;

public class ThemisService {

	
	public static void executeTests(String clientId, String nOnt, Path out) throws IOException {
		
		Path ontology = Paths.get("tmp//ontologies//" + clientId + "//" + nOnt + ".rdf");
		Path test = Paths.get("tmp//ontologies//" + clientId + "//tests//" + nOnt + ".test");
		
		if(!Files.exists(test)) {
			generateTests(ontology, test);
			Files.write(out.resolve("tests.ttl"), 
					Files.readAllLines(test).stream().reduce((x,y)->x+ " " + y).orElse("").getBytes());
		}
		
		ThemisResource ts = new ThemisResource();
		String res = ts.executeTests(Files.readAllLines(ontology).stream().reduce((x,y)->x+ " " + y).orElse(""), Files.readAllLines(test).stream().reduce((x,y)->x+ " " + y).orElse(""));

		Files.write(out.resolve("results.json"), res.getBytes());
	}

	private static void generateTests(Path ontology, Path out) throws IOException {
		ThemisResource ts = new ThemisResource();
		String res = ts.getTests(Files.readAllLines(ontology).stream().reduce((x,y)->x+ " " + y).orElse(""));		
		Files.write(out, res.getBytes());

	}
	
	
}
