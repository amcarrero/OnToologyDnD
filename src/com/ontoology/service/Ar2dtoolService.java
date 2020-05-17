package com.ontoology.service;

import java.nio.file.Files;
import java.nio.file.Path;
import com.ontoology.util.Configuration;

public class Ar2dtoolService {
static String ar2dtool_dir = Configuration.AR2DTOOL_DIR;
static String conf1 = Configuration.AR2DTOOL_CONF1;
static String conf2 = Configuration.AR2DTOOL_CONF2;

	public static void createDiagrams(String ontology, Path out) throws Exception {
		Path out1 = out.resolve("taxonomy");
		Files.createDirectory(out1);
		out1 = out1.resolve("ar2d.png");
		
		Path out2 = out.resolve("classes");
		Files.createDirectory(out2);
		out2 = out2.resolve("ar2d.png");
		
		String comm = "java -jar " + ar2dtool_dir + " -i "
				+ ontology + " -o " + out1 + " -t " + " png " + " -c "
				+ conf1  + " -GV -gml";
		
		String comm2 = "java -jar " + ar2dtool_dir + " -i "
				+ ontology + " -o " + out2 + " -t " + " png	 " + " -c "
				+ conf2 +  " -GV -gml";

		
		Process p = Runtime.getRuntime().exec(comm);
	    p.waitFor();
	    
		Process p2 = Runtime.getRuntime().exec(comm2);   
		p2.waitFor();
		
	    if(p.exitValue()!=0 || p2.exitValue()!=0)
	    	throw new Exception();

	}
}
