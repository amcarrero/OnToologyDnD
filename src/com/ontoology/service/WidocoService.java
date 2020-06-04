package com.ontoology.service;

import com.ontoology.util.Configuration;

public class WidocoService {

	static String widoco_dir = Configuration.WIDOCO_DIR;

	
	public static void createWidocoDoc(String ontology, String out) throws Exception {
		String comm = "java -jar -Dfile.encoding=utf-8 " + widoco_dir + " -rewriteAll"
				+ " -ontFile " + ontology + " -outFolder " + out + " -getOntologyMetadata "
				+ " -licensius " + " -htaccess ";
		Process p = Runtime.getRuntime().exec(comm);
	    p.waitFor();
	    if(p.exitValue()!=0)
	    	throw new Exception("Widoco return an exit code: " + p.exitValue());
	}
	
}
