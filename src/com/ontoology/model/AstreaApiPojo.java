package com.ontoology.model;

public class AstreaApiPojo {
	private String ontology;
	private String serialisasion;
	
	
	public AstreaApiPojo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public AstreaApiPojo(String ontology, String serialisasion) {
		super();
		this.ontology = ontology;
		this.serialisasion = serialisasion;
	}
	public String getOntology() {
		return ontology;
	}
	public void setOntology(String ontology) {
		this.ontology = ontology;
	}
	public String getSerialisasion() {
		return serialisasion;
	}
	public void setSerialisasion(String serialisasion) {
		this.serialisasion = serialisasion;
	}
	
	
	
}
