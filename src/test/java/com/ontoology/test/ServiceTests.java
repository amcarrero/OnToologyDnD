package test.java.com.ontoology.test;


import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import main.java.com.ontoology.resources.ThemisResource;
import main.java.com.ontoology.service.AstreaService;
import main.java.com.ontoology.service.OopsService;
import main.java.com.ontoology.service.WidocoService;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class ServiceTests {

	private Path out;
	private Path ontology;

	@BeforeEach
	void setup() throws URISyntaxException {
		URL ontologyUri = this.getClass().getClassLoader().getResource("ontology.ttl");
		ontology = Paths.get(ontologyUri.toURI());
		out = Paths.get("out");

	}

	@AfterEach
	void clean() throws IOException {
		FileUtils.cleanDirectory(out.toFile());
	}

	@Test
	public void shouldExecuteWidoco() throws Exception {
		WidocoService.createWidocoDoc(ontology.toString(), out.toString());
	}

	@Test
	public void shouldExecuteOops() throws Exception {
		OopsService os = new OopsService();
		os.generateOopsEval(ontology, out, "Ontology test");
	}

	@Test
	public void shouldExecuteAstrea() throws Exception {
		AstreaService.generateShapes(ontology, out);
	}

	@Test
	public void shouldExecuteThemis() throws Exception {
		ThemisResource tr = new ThemisResource();
		String test = tr.getTests(Files.readAllLines(ontology).stream().reduce((x,y)->x+ " " + y).orElse(""));
		tr.executeTests(Files.readAllLines(ontology).stream().reduce((x,y)->x+ " " + y).orElse(""), test);
	}

}
