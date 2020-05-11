package com.ontoology.model;

public class ThemisApiPojo {
	private String[] ontologiesCode;
	private String testfile;
	public String[] getOntologiesCode() {
		return ontologiesCode;
	}
	public void setOntologiesCode(String[] ontologiesCode) {
		this.ontologiesCode = ontologiesCode;
	}
	public String getTestfile() {
		return testfile;
	}
	public void setTestfile(String testfile) {
		this.testfile = testfile;
	}
	public ThemisApiPojo(String[] ontologiesCode, String testfile) {
		super();
		this.ontologiesCode = ontologiesCode;
		this.testfile = testfile;
	}
	public ThemisApiPojo() {
		super();
	}
	
	
	
}
