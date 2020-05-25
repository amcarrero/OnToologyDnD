package com.ontoology.test;


import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

import com.ontoology.resources.ThemisResource;
import com.ontoology.service.Ar2dtoolService;
import com.ontoology.service.AstreaService;
import com.ontoology.service.OopsService;
import com.ontoology.service.WidocoService;

public class ServiceTests {

	@Test
	public void widocoTest() throws Exception {
		Path ontology = Paths.get("TestFiles//ontology.ttl");
		Path out = Paths.get("TestFiles//out");
		WidocoService.createWidocoDoc(ontology.toString(), out.toString());
		FileUtils.cleanDirectory(out.toFile());
	}
	
	@Test
	public void ar2dtoolTest() throws Exception {
		Path ontology = Paths.get("TestFiles//ontology.ttl");
		Path out = Paths.get("TestFiles//out");
		Ar2dtoolService.createDiagrams(ontology.toString(), out);
		FileUtils.cleanDirectory(out.toFile());
	}
	
	@Test
	public void oopsTest() throws Exception {
		Path ontology = Paths.get("TestFiles//ontology.ttl");
		Path out = Paths.get("TestFiles//out");
		OopsService os = new OopsService();
		os.generateOopsEval(ontology, out, "Ontology test");
		FileUtils.cleanDirectory(out.toFile());
	}
		
	@Test
	public void astreaTest() throws Exception {
		Path ontology = Paths.get("TestFiles//ontology.ttl");
		Path out = Paths.get("TestFiles//out");
		AstreaService.generateShapes(ontology, out);
		FileUtils.cleanDirectory(out.toFile());
	}
	
	@Test
	public void themisTest() throws Exception {
		Path ontology = Paths.get("TestFiles//ontology.ttl");
		Path out = Paths.get("TestFiles//out");
		ThemisResource tr = new ThemisResource();
		String test = tr.getTests(Files.readAllLines(ontology).stream().reduce((x,y)->x+ " " + y).orElse(""));
		tr.executeTests(Files.readAllLines(ontology).stream().reduce((x,y)->x+ " " + y).orElse(""), test);
		FileUtils.cleanDirectory(out.toFile());
	}

}
