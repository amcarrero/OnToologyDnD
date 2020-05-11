package com.ontoology.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import com.ontoology.resources.AstreaResource;

public class AstreaService {

	public static void generateShapes(Path ontology, Path out) throws IOException {
		
		AstreaResource ar = new AstreaResource();
		
		String res = ar.getShaclShapes(Files.readAllLines(ontology).stream().reduce((x,y)->x+ " " + y).orElse(""));
		
		out = out.resolve("astrea-shapes.ttl");
		
		Files.write(out, res.getBytes());

	}
}
