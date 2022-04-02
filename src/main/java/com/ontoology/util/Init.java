package main.java.com.ontoology.util;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.eclipse.jgit.api.Git;
import org.springframework.stereotype.Component;

@Component
public class Init implements ServletContextListener{

    private static final Logger LOG = Logger.getLogger(Init.class.getName());


	@Override
	//
	//Tasks to be executed by the server at startup
	//create directories and download the necessary libraries from GitHub ( except graphviz)
	public void contextInitialized(ServletContextEvent sce) {
		try {
			LOG.info("Initializing OnToology");

			Path libraries = Paths.get("libraries");
				File dir = Files.createDirectories(libraries).toFile();
				Git.cloneRepository()
					  .setURI( "https://github.com/amcarrero/ontoologyD-DLibs.git" )
					  .setDirectory(dir)
					  .call();

		}catch(Exception e) {
			LOG.warning("Error initializing OnToology or Libraries already downloaded");
		}
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub

	}
}
