package com.ontoology.util;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.util.Arrays;
import java.util.Date;
import java.util.logging.Logger;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.FileSystemUtils;

@Component
public class DeleteDirectoriesScheduled {
	
    private static final Logger LOG = Logger.getLogger(DeleteDirectoriesScheduled.class.getName());
	
    //Deletes user directories that have not been used for 30 minutes
	@Scheduled(initialDelay = 1800000, fixedRate = 1800000)
	public void deleteDirs()  {
		
		LOG.info("Deleting old users ontologies");
		
		Path p = Paths.get("tmp//ontologies");
		
		File folder = p.toFile();
				
				
		File[] files = folder.listFiles();
		
		Arrays.asList(files).forEach(x -> {
			Date lastModifiedDate = new Date(x.lastModified());
			Instant lastModified = lastModifiedDate.toInstant();		
			
			Date now = new Date();
			Instant nowMinus30Minutes = now.toInstant().minusSeconds(1800);
			
			if(lastModified.isBefore(nowMinus30Minutes)) {
				FileSystemUtils.deleteRecursively(x);
			}
		});
		
		
	    
	}

}
