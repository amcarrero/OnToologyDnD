package com.ontoology.service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.RDFWriterI;
import org.springframework.web.multipart.MultipartFile;

import com.ontoology.util.Configuration;
import com.ontoology.util.ZipDirectory;

public class OnToologyService {

	public static void widoco(String clientId, String nOnt, String originalName) throws Exception {
		Path ontoology = Paths.get("tmp//ontologies//" + clientId + "//OnToology//");
		if (!Files.exists(ontoology))
			Files.createDirectory(ontoology);

		Path ontology = Paths.get("tmp//ontologies//" + clientId + "//" + nOnt + ".rdf");

		Path outDir = ontoology.resolve(nOnt + "-" + originalName);

		if (!Files.exists(outDir))
			Files.createDirectory(outDir);

		Path widocoDir = outDir.resolve("widoco");
		if (!Files.exists(widocoDir))
			Files.createDirectory(widocoDir);

		WidocoService.createWidocoDoc(ontology.toString(), widocoDir.toString());

	}

	public static void astrea(String clientId, String nOnt, String originalName) throws Exception {
		Path ontoology = Paths.get("tmp//ontologies//" + clientId + "//OnToology//");
		if (!Files.exists(ontoology))
			Files.createDirectory(ontoology);

		Path ontology = Paths.get("tmp//ontologies//" + clientId + "//" + nOnt + ".rdf");

		Path outDir = ontoology.resolve(nOnt + "-" + originalName);

		if (!Files.exists(outDir))
			Files.createDirectory(outDir);

		Path astreaDir = outDir.resolve("astrea");

		if (!Files.exists(astreaDir))
			Files.createDirectory(astreaDir);

		AstreaService.generateShapes(ontology, astreaDir);

	}

	public static void themis(String clientId, String nOnt, String originalName) throws Exception {
		Path ontoology = Paths.get("tmp//ontologies//" + clientId + "//OnToology//");
		if (!Files.exists(ontoology))
			Files.createDirectory(ontoology);

		Path outDir = ontoology.resolve(nOnt + "-" + originalName);

		if (!Files.exists(outDir))
			Files.createDirectory(outDir);

		Path themisDir = outDir.resolve("themis");

		if (!Files.exists(themisDir))
			Files.createDirectory(themisDir);

		ThemisService.executeTests(clientId, nOnt, themisDir);
	}

	public static void oops(String clientId, String nOnt, String originalName) throws Exception {
		Path ontoology = Paths.get("tmp//ontologies//" + clientId + "//OnToology//");
		if (!Files.exists(ontoology))
			Files.createDirectory(ontoology);

		Path ontology = Paths.get("tmp//ontologies//" + clientId + "//" + nOnt + ".rdf");

		Path outDir = ontoology.resolve(nOnt + "-" + originalName);

		if (!Files.exists(outDir))
			Files.createDirectory(outDir);

		Path oopsDir = outDir.resolve("oops");
		if (!Files.exists(oopsDir)) {
			Files.createDirectory(oopsDir);
			Files.createDirectory(oopsDir.resolve("evaluation"));
		}

		OopsService os = new OopsService();
		os.generateOopsEval(ontology, oopsDir, originalName);
		ZipDirectory.unzipDir(Configuration.OOPS_DIR, oopsDir.resolve("evaluation").toString());

	}

	public static void zipDirectory(String clientId) throws IOException {

		Path directory = Paths.get("tmp//ontologies//" + clientId + "//OnToology//");
		Path out = Paths.get("tmp//ontologies//" + clientId + "//OnToology.zip");

		ZipDirectory.zipDirectory(directory.toString(), out.toString());

	}

	public static void saveOntology(MultipartFile file, String clientId) throws IOException {
		if (!file.isEmpty())
		try {

				Model model = ModelFactory.createDefaultModel();
				model.read(file.getInputStream(), null, "RDF/XML");
				if(model.size() == 0)
					throw new IllegalArgumentException();
				Path path = Paths.get("tmp//ontologies//" + clientId);
				if (!Files.exists(path))
					Files.createDirectory(path);
				if (!Files.exists(path.resolve("tests")))
					Files.createDirectory(path.resolve("tests"));
				Long nOntologias = Files.list(path).count();
				path = Paths.get("tmp//ontologies//" + clientId + "//" + nOntologias + ".rdf");

				RDFWriterI writer = model.getWriter("RDF/XML");
				writer.setProperty("showXmlDeclaration", "true");
				FileOutputStream fos = new FileOutputStream(path.toString());
				OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");
				writer.write(model, osw, null);


		} catch (Exception pe) {
			try {

					Model model = ModelFactory.createDefaultModel();
					model.read(file.getInputStream(), null, "TTL");
					if(model.size() == 0)
						throw new IllegalArgumentException();
					Path path = Paths.get("tmp//ontologies//" + clientId);
					if (!Files.exists(path))
						Files.createDirectory(path);
					if (!Files.exists(path.resolve("tests")))
						Files.createDirectory(path.resolve("tests"));
					Long nOntologias = Files.list(path).count();
					path = Paths.get("tmp//ontologies//" + clientId + "//" + nOntologias + ".rdf");

					RDFWriterI writer = model.getWriter("RDF/XML");
					writer.setProperty("showXmlDeclaration", "true");
					FileOutputStream fos = new FileOutputStream(path.toString());
					OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");
					writer.write(model, osw, null);

			} catch (Exception e) {

					Model model = ModelFactory.createDefaultModel();
					model.read(file.getInputStream(), null, "N-TRIPLE");
					if(model.size() == 0)
						throw new IllegalArgumentException();
					Path path = Paths.get("tmp//ontologies//" + clientId);
					if (!Files.exists(path))
						Files.createDirectory(path);
					if (!Files.exists(path.resolve("tests")))
						Files.createDirectory(path.resolve("tests"));
					Long nOntologias = Files.list(path).count();
					path = Paths.get("tmp//ontologies//" + clientId + "//" + nOntologias + ".rdf");

					RDFWriterI writer = model.getWriter("RDF/XML");
					writer.setProperty("showXmlDeclaration", "true");
					FileOutputStream fos = new FileOutputStream(path.toString());
					OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");
					writer.write(model, osw, null);

			}
		}
	}

	public static void saveTest(MultipartFile file, String clientId, String nOnt) throws IOException {

		Model model = ModelFactory.createDefaultModel();
		model.read(file.getInputStream(), null, "TTL");
		if(model.size() == 0)
			throw new IllegalArgumentException();
		Path path = Paths.get("tmp//ontologies//" + clientId);
		if (!Files.exists(path))
			Files.createDirectory(path);
		if (!Files.exists(path.resolve("tests")))
			Files.createDirectory(path.resolve("tests"));

		path = Paths.get("tmp//ontologies//" + clientId + "//tests//" + nOnt + ".test");

		RDFWriterI writer = model.getWriter("TTL");
		writer.setProperty("showXmlDeclaration", "true");
		FileOutputStream fos = new FileOutputStream(path.toString());
		OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");
		writer.write(model, osw, null);

	}
}
