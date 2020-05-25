package com.ontoology.service;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.jena.ontology.DatatypeProperty;
import org.apache.jena.ontology.Individual;
import org.apache.jena.ontology.ObjectProperty;
import org.apache.jena.ontology.OntClass;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.NodeIterator;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.rdf.model.ResIterator;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.util.iterator.ExtendedIterator;

import com.ontoology.resources.OopsResource;

public class OopsService {
	
	public OntModel model = null;
	
	public void generateOopsEval(Path ontology, Path out, String title) throws Exception {
		
		
		String res = OopsResource.getOopsReport(Files.readAllLines(ontology).stream().reduce((x,y)->x+ " " + y).orElse(""));
		
		Files.write(out.resolve("oops.txt"), res.getBytes());
		
		out = out.resolve("oops.html");
		
		OntModelSpec s = new OntModelSpec(OntModelSpec.OWL_MEM);
		
		this.model = ModelFactory.createOntologyModel(s);
		
		InputStream stream = new ByteArrayInputStream(res.getBytes(StandardCharsets.UTF_8));

		this.model.read(stream, "http://myevaluation.com#");
		
		if(!res.equals("Oops! service is not available at this moment, please try again later")) {			
			String html = getOopsHtml(printEvaluation(), title);
			Files.write(out, html.getBytes());
		}else {
			Files.write(out, res.getBytes());
		}

	}
	
	//Method extracted from widoco and updated to the new oops api
	private String printEvaluation() {
		
		String oops = "http://oops.linkeddata.es/def#";

		String evaluationOutput = "";

		OntClass pitfallClass = model.createClass(oops + "pitfall");
		DatatypeProperty hasCodeDTP = model.createDatatypeProperty(oops + "hasCode");
		// DatatypeProperty hasTitleDTP = model.createDatatypeProperty( oops +
		// "hasTitle");
		DatatypeProperty hasNameDTP = model.createDatatypeProperty(oops + "hasName");
		DatatypeProperty hasDescriptionDTP = model.createDatatypeProperty(oops + "hasDescription");
		DatatypeProperty hasImportanceLevelDTP = model.createDatatypeProperty(oops + "hasImportanceLevel");
		DatatypeProperty hasFrequencyDTP = model.createDatatypeProperty(oops + "hasNumberAffectedElements");
		ObjectProperty hasAffectedElement = model.createObjectProperty(oops + "hasAffectedElement");
		ObjectProperty mightNotBeInverseOf = model.createObjectProperty(oops + "mightNotBeInverseOf");
		ObjectProperty hasEquivalentClass = model.createObjectProperty(oops + "hasEquivalentClass");
		ObjectProperty hasWrongEquivalentClass = model.createObjectProperty(oops + "hasWrongEquivalentClass");
		ObjectProperty noSuggestion = model.createObjectProperty(oops + "noSuggestion");
		ObjectProperty haveSameLabel = model.createObjectProperty(oops + "haveSameLabel");

		ExtendedIterator<Individual> p = model.listIndividuals(pitfallClass);

		List<Individual> plist = p.toList();

		if (plist.size() > 0) {
			
			// prepare for order list

			List<String> codesL = new ArrayList<>();

			for (int k = 0; k < plist.size(); k++) {
				if (plist.get(k).hasProperty(hasCodeDTP)) {
					codesL.add(plist.get(k).getPropertyValue(hasCodeDTP).asLiteral().getString());
				} else {
				}
			}

			Collections.sort(codesL);


			evaluationOutput = evaluationOutput + "<h2>Evaluation results</h2>\n";
			evaluationOutput = evaluationOutput + "<div class=\"panel-group\" id=\"accordion\">\n";

			// for (int i = 0; i < plist.size(); i++){
			int i = 0;
			for (String temp : codesL) {
				// Individual ind = plist.get(i);
				ResIterator resources = model.listSubjectsWithProperty(hasCodeDTP, temp);

				if (resources.hasNext()) {
					Individual ind = resources.next().as(Individual.class);

					// Iterator a = ind.listProperties();
					// while (a.hasNext()){
					// System.out.println(a.next().toString());
					//
					// }

					String title = ind.getPropertyValue(hasNameDTP).asLiteral().getString();
					String code = ind.getPropertyValue(hasCodeDTP).asLiteral().getString();
					String description = ind.getPropertyValue(hasDescriptionDTP).asLiteral().getString();
					String importanceLevel = ind.getPropertyValue(hasImportanceLevelDTP).asLiteral().getString();

					boolean hasFrequency = ind.hasProperty(hasFrequencyDTP);
					int frequency = 0;

					if (hasFrequency) {
						frequency = ind.getPropertyValue(hasFrequencyDTP).asLiteral().getInt();
					}

					// if(ind.hasProperty(hasTitleDTP)){
					// title = ind.getPropertyValue(hasTitleDTP).asLiteral().getString();
					// }

					// codigo y titulo
					evaluationOutput = evaluationOutput + "<div class=\"panel panel-default\">\n";
					evaluationOutput = evaluationOutput + "<div class=\"panel-heading\">\n";
					evaluationOutput = evaluationOutput + "<h4 class=\"panel-title\">\n";
					evaluationOutput = evaluationOutput + "<a data-toggle=\"collapse\" href=\"#collapse" + i + "\">\n";
					evaluationOutput = evaluationOutput + code + ". " + title;

					// frequency and important level
					// evaluationOutput = evaluationOutput + "</a>\n";

					// place stuff at the right
					evaluationOutput = evaluationOutput + "<span style=\"float: right;\">";

					if (code.contentEquals("P03") || code.contentEquals("P10") || code.contentEquals("P22")
							|| code.contentEquals("P36") || code.contentEquals("P37") || code.contentEquals("P38")
							|| code.contentEquals("P39")) {
						evaluationOutput = evaluationOutput + " ontology *";
					} else if (frequency == 1) {
						evaluationOutput = evaluationOutput + frequency + " case detected. ";

					} else {
						evaluationOutput = evaluationOutput + frequency + " cases detected. ";
					}

					if (importanceLevel.equalsIgnoreCase("critical")) {
						evaluationOutput = evaluationOutput + "<span class=\"label label-danger\">" + importanceLevel
								+ "</span>";
					} else if (importanceLevel.equalsIgnoreCase("important")) {
						evaluationOutput = evaluationOutput + "<span class=\"label label-warning\">" + importanceLevel
								+ "</span>";
					} else if (importanceLevel.equalsIgnoreCase("minor")) {
						evaluationOutput = evaluationOutput + "<span class=\"label label-minor\">" + importanceLevel
								+ "</span>";
					}

					// end stuff at right
					evaluationOutput = evaluationOutput + "</span>";

					evaluationOutput = evaluationOutput + "</a>\n";
					evaluationOutput = evaluationOutput + "</h4>\n";
					evaluationOutput = evaluationOutput + "</div>\n";
					evaluationOutput = evaluationOutput + "<div id=\"collapse" + i
							+ "\" class=\"panel-collapse collapse\">\n";
					evaluationOutput = evaluationOutput + "<div class=\"panel-body\">\n";
					// descripcion
					evaluationOutput = evaluationOutput + "<p>" + description + "</p>";

					// affected elements
					if (code.contentEquals("P10") || code.contentEquals("P22") || code.contentEquals("P37")
							|| code.contentEquals("P38") || code.contentEquals("P39")) {
						evaluationOutput = evaluationOutput + "<p>"
								+ "*This pitfall applies to the ontology in general instead of specific elements"
								+ "</p>";
					} else if (code.contentEquals("P03")) {
						Resource affectedE = ind.getPropertyResourceValue(hasAffectedElement);
						evaluationOutput = evaluationOutput + "<p>" + "The property " + "<a href=\""
								+ affectedE.getURI() + "\" target=\"_blank\">" + affectedE.getURI() + "</a>"
								+ " might be replaced by an ontology language predicate as for example "
								+ "\"rdf:type\" or \"rdfs:subclassOf\" or  \"owl:sameAs\"" + "</p>";
					}
					else if (code.contentEquals("P36")) {
						evaluationOutput = evaluationOutput + "<p>"
								+ "*This pitfall applies to the ontology in general instead of specific elements and it appears in the ontology URI: "
								+ "<a href=\"" + "" + "\" target=\"_blank\">" + "" + "</a>"
								+ "</p>";
					}

					else {
						evaluationOutput = evaluationOutput + "<p>"
								+ "This pitfall affects to the following ontology elements: " + "</p>";
						if (code.contentEquals("P05")) {
							NodeIterator elements = ind.listPropertyValues(mightNotBeInverseOf);

							evaluationOutput = evaluationOutput + "<ul>";

							while (elements.hasNext()) {
								String uri = elements.next().asResource().getURI();
								Individual indi = model.getIndividual(uri);

								NodeIterator elementos = indi.listPropertyValues(hasAffectedElement);
								String first = elementos.next().asLiteral().getString();
								String second = elementos.next().asLiteral().getString();
								evaluationOutput = evaluationOutput + "<li>" + "<a href=\"" + first
										+ "\" target=\"_blank\">" + first + "</a>" + " may not be inverse of "
										+ "<a href=\"" + second + "\" target=\"_blank\">" + second + "</a>" + "</li>";
							}

							evaluationOutput = evaluationOutput + "</ul>";
						} else if (code.contentEquals("P13")) {

							NodeIterator elements = ind.listPropertyValues(noSuggestion);

							evaluationOutput = evaluationOutput + "<ul>";

							while (elements.hasNext()) {
								String uri = elements.next().asResource().getURI();
								Individual indi = model.getIndividual(uri);

								NodeIterator elementos = indi.listPropertyValues(hasAffectedElement);
								while (elementos.hasNext()) {
									String first = elementos.next().asLiteral().getString();
									evaluationOutput = evaluationOutput + "<li>" + "<a href=\"" + first
											+ "\" target=\"_blank\">" + first + "</a>" + "</li>";
								}
							}

							evaluationOutput = evaluationOutput + "<ul>";

						} else if (code.contentEquals("P30")) {

							NodeIterator elements = ind.listPropertyValues(hasEquivalentClass);

							evaluationOutput = evaluationOutput + "<ul>";

							while (elements.hasNext()) {
								String uri = elements.next().asResource().getURI();
								Individual indi = model.getIndividual(uri);

								NodeIterator elementos = indi.listPropertyValues(hasAffectedElement);

								String first = elementos.next().asLiteral().getString();
								String second = elementos.next().asLiteral().getString();
								evaluationOutput = evaluationOutput + "<li>" + "<a href=\"" + first
										+ "\" target=\"_blank\">" + first + "</a>" + " , " + "<a href=\"" + second
										+ "\" target=\"_blank\">" + second + "</a>" + "</li>";
							}

							evaluationOutput = evaluationOutput + "<ul>";

						} else if (code.contentEquals("P31")) {

							NodeIterator elements = ind.listPropertyValues(hasWrongEquivalentClass);

							evaluationOutput = evaluationOutput + "<ul>";

							while (elements.hasNext()) {
								String uri = elements.next().asResource().getURI();
								Individual indi = model.getIndividual(uri);

								NodeIterator elementos = indi.listPropertyValues(hasAffectedElement);

								String first = elementos.next().asLiteral().getString();
								String second = elementos.next().asLiteral().getString();
								evaluationOutput = evaluationOutput + "<li>" + "<a href=\"" + first
										+ "\" target=\"_blank\">" + first + "</a>" + " , " + "<a href=\"" + second
										+ "\" target=\"_blank\">" + second + "</a>" + "</li>";
							}

							evaluationOutput = evaluationOutput + "<ul>";

						} else if (code.contentEquals("P32")) {

							NodeIterator elements = ind.listPropertyValues(haveSameLabel);

							evaluationOutput = evaluationOutput + "<ul>";

							while (elements.hasNext()) {
								String uri = elements.next().asResource().getURI();
								Individual indi = model.getIndividual(uri);

								NodeIterator elementos = indi.listPropertyValues(hasAffectedElement);
								evaluationOutput = evaluationOutput + "<li>";
								boolean primero = true;
								while (elementos.hasNext()) {
									String first = elementos.next().asLiteral().getString();
									if (!primero)
										evaluationOutput = evaluationOutput + " , ";
									evaluationOutput = evaluationOutput + "<a href=\"" + first + "\" target=\"_blank\">"
											+ first + "</a>";
									primero = false;
								}
								evaluationOutput = evaluationOutput + "</li>";
							}

							evaluationOutput = evaluationOutput + "<ul>";

						} else {
							NodeIterator elements = ind.listPropertyValues(hasAffectedElement);

							evaluationOutput = evaluationOutput + "<ul>";

							while (elements.hasNext()) {
								RDFNode nextNode = elements.next();

								if (nextNode.isLiteral()) {
									String element = nextNode.asLiteral().getString();
									evaluationOutput = evaluationOutput + "<li>" + "<a href=\"" + element
											+ "\" target=\"_blank\">" + element + "</a>" + "</li>";
								} else if (nextNode.isURIResource()) {
									System.out.println("Es un Resource in OOPSevaluation");

								} else {
									System.out.println("Can't act as Individual in OOPSevaluation");
								}

							}
							evaluationOutput = evaluationOutput + "</ul>";
						}
					}

					evaluationOutput = evaluationOutput + "</div>\n";
					evaluationOutput = evaluationOutput + "</div>\n";
					evaluationOutput = evaluationOutput + "</div>\n";

					i++;
				}
			}
			evaluationOutput = evaluationOutput + "</div>\n"; // close div accordion
		} else {
			evaluationOutput = "<h2>Congratulations! OOPS did not find a single pitfall</h2>";
		}

		return evaluationOutput;

	}
	
	private static String getOopsHtml(String evaluationContent, String title) {
		String eval = "<!DOCTYPE html>\n" + "<html lang=\"en\">\n" + "  <head>\n" + "    <meta charset=\"UTF-8\">\n"
				+ "    <title>" +title + "</title>\n"
				+ "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n"
				+ "    <meta name=\"description\" content=\"Evaluation of the ontology with the OOPS tool.\">\n"
				+ "    <meta name=\"Languaje\" content=\"English\">\n" + "    \n"
				+ "    <script src=\"evaluation/jquery-1.11.0.js\"></script>\n"
				+ "    <script src=\"evaluation/bootstrap.min.js\"></script>\n"
				+ "    <link rel=\"stylesheet\" href=\"evaluation/style.css\" type=\"text/css\" media=\"print, projection, screen\" />\n"
				+ "    <script type=\"text/javascript\" src=\"evaluation/jquery.tablesorter.min.js\"></script>\n"
				+ "    <script type=\"text/javascript\" id=\"js\">\n" + "	    $(document).ready(function() \n"
				+ "		    { \n" + "		    	$(\"#tablesorter-demo\").tablesorter(); \n"
				+ "		    	$('.collapse').collapse({ \n" + "		    	toggle: false\n"
				+ "		    	});\n" + "		    } \n" + "	    ); \n" + "    </script>\n" + "\n"
				+ "    <link href=\"evaluation/bootstrap.css\" rel=\"stylesheet\">\n"
				+ "    <style type=\"text/css\">\n" + "      body {\n" +
				"        padding-bottom: 40px;\n" + "      }\n" + "    </style>\n"
				+ "    <link href=\"evaluation/bootstrap-responsive.css\" rel=\"stylesheet\">\n" + "    \n"
				+ "    <!-- HTML5 shim, for IE6-8 support of HTML5 elements -->\n" + "    <!--[if lt IE 9]>\n"
				+ "      <script src=\"/dist/js/html5shiv.js\"></script>\n" + "    <![endif]-->\n" + "\n"
				+ "  </head>\n" + "<div class=\"container\">\n" + "<h1>"
				+  title + "</h1>\n" + "<br>\n"
				+ "<dl class=\"dl-horizontal\">\n" + "<dt>Title</dt>\n" + "<dd>"
				+    title + "</dd>\n" + "\n"
				+ "<p> The following evaluation results have been generated by the <a href = \"http://oops-ws.oeg-upm.net/\" target=\"_blank\">RESTFul web service</a> provided by <a href = \"http://oops.linkeddata.es\" target=\"_blank\">OOPS! (OntOlogy Pitfall Scanner!)</a>.</p>"
				+ "<p>\n"
				+ "<a href=\"http://oops.linkeddata.es\" target=\"_blank\"><img src=\"http://vocab.linkeddata.es/ontologies/oops/logomini.png\" alt=\"OOPS! logo\" class=\"img-rounded\" class=\"img-responsive\" /></a>"
				+ "It is obvious that not all the pitfalls are equally important; their impact in the ontology "
				+ "will depend on multiple factors. For this reason, each pitfall has an importance level "
				+ "attached indicating how important it is. We have identified three levels:" + "</p>\n" + "\n"
				+ "<dl class=\"dl-horizontal\">\n" + "<dt><span class=\"label label-danger\">Critical</span></dt>\n"
				+ "<dd>It is crucial to correct the pitfall. Otherwise, it could affect the ontology consistency, reasoning, applicability, etc.</dd>\n"
				+ "\n"
				+ "<dt><span class=\"label label-warning\">Important</span></dt> <dd> Though not critical for ontology function, it is important to correct this type of pitfall.</dd>\n"
				+ "\n"
				+ "<dt><span class=\"label label-minor\">Minor</span></dt> <dd>It is not really a problem, but by correcting it we will make the ontology nicer.</dd>\n"
				+ "</dl>" + evaluationContent +
				// references
				"<p>References:</p>\n" + "    <ul>\n" + "    <li>\n"
				+ "    [1] G&oacute;mez-P&oacute;rez, A. Ontology Evaluation. Handbook on Ontologies. S. Staab and R. Studer Editors. Springer. International Handbooks on Information Systems. Pp: 251-274. 2004.\n"
				+ "    </li> \n" + "    <li>\n"
				+ "    [2] Noy, N.F., McGuinness. D. L. Ontology development 101: A guide to creating your first ontology. Technical Report SMI-2001-0880, Standford Medical Informatics. 2001.\n"
				+ "    </li> \n" + "    <li>\n"
				+ "    [3] Rector, A., Drummond, N., Horridge, M., Rogers, J., Knublauch, H., Stevens, R.,; Wang, H., Wroe, C. ''Owl pizzas: Practical experience of teaching owl-dl: Common errors and common patterns''. In Proc. of EKAW 2004, pp: 63-81. Springer. 2004.\n"
				+ "    </li>\n" + "    <li>\n"
				+ "    [4] Hogan, A., Harth, A., Passant, A., Decker, S., Polleres, A. Weaving the Pedantic Web. Linked Data on the Web Workshop LDOW2010 at WWW2010 (2010).\n"
				+ "    </li>\n" + "     <li>\n"
				+ "    [5] Archer, P., Goedertier, S., and Loutas, N. D7.1.3 - Study on persistent URIs, with identification of best practices and recommendations on the topic for the MSs and the EC. Deliverable. December 17, 2012.\n"
				+ "    </li>\n" + "    <li>\n"
				+ "    [6] Heath, T., Bizer, C.: Linked data: Evolving the Web into a global data space (1st edition). Morgan &amp; Claypool (2011).\n"
				+ "    </li>\n" + "    </ul>\n" +
				"<footer>\n" + "            <div class=\"row\">\n" + "    	<div class=\"col-md-7\">\n"
				+ "    		Developed by 	        <a href = \"http://delicias.dia.fi.upm.es/members/mpoveda/\" target=\"_blank\">Mar&iacutea Poveda</a>\n"
				+ "	        <br>\n"
				+ "    	Built with <a target=\"_blank\" href=\"http://getbootstrap.com/\">Bootstrap</a>\n"
				+ "	        <br>\n"
				+ "	        <br>\n" + "        </div>\n" + "    	<div class=\"col-md-5\">\n"
				+ "		<p class=\"text-right\"> Developed with: </p>\n" + "		<p class=\"text-right\">\n"
				+ "     		<a href=\"http://oops.linkeddata.es\" target=\"_blank\"><img src=\"http://vocab.linkeddata.es/ontologies/oops/logomini.png\" alt=\"OOPS! logo\" class=\"img-rounded\" class=\"img-responsive\" /></a>\n"
				+ "    	</p>\n" + "    	</div>\n" + "      </div>\n" + "      </footer>\n"
				+ "    </div> <!-- /container -->\n";
		return eval;
	}


}
