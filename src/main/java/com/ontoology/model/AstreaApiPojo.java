package main.java.com.ontoology.model;

public class AstreaApiPojo {
	private String ontology;
	private String serialisation;


	public AstreaApiPojo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public AstreaApiPojo(String ontology, String serialisation) {
		super();
		this.ontology = ontology;
		this.serialisation = serialisation;
	}
	public String getOntology() {
		return ontology;
	}
	public void setOntology(String ontology) {
		this.ontology = ontology;
	}
	public String getSerialisation() {
		return serialisation;
	}
	public void setSerialisation(String serialisation) {
		this.serialisation = serialisation;
	}



}
