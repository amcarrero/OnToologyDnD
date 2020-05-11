package com.ontoology.util;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.eclipse.jgit.api.Git;

public class Init implements ServletContextListener{

    private static final Logger LOG = Logger.getLogger(Init.class.getName());


	@Override
	//
	//Tasks to be executed by the server at startup
	//create directories and download the necessary libraries from GitHub ( except graphviz)
	public void contextInitialized(ServletContextEvent sce) {
		try {
			LOG.info("Initializing OnToology");
	
			Path tmp = Paths.get("tmp");
			if(!Files.exists(tmp))
				Files.createDirectories(tmp);
			Path ontologies = tmp.resolve("ontologies");
			if(!Files.exists(ontologies))
				Files.createDirectories(ontologies);
			Path libraries = Paths.get("libraries");
			if(!Files.exists(libraries)) {
				Files.createDirectories(libraries);
				Git.cloneRepository()
					  .setURI( "https://github.com/amcarrero/ontoologyD-DLibs.git" )
					  .setDirectory(libraries.toFile())
					  .call();
			}
		}catch(Exception e) {
			LOG.warning("Error initializing OnToology");    
		}	
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		
	}
}


	

