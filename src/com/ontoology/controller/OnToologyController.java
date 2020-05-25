package com.ontoology.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ontoology.service.OnToologyService;

@Controller
public class OnToologyController {
	
    private static final Logger LOG = Logger.getLogger(OnToologyController.class.getName());
			
	@GetMapping(value="/app")
	public ModelAndView index(HttpServletRequest request) {
		return new ModelAndView("index");
	}
	
	@GetMapping(value="/status")
	public ModelAndView stat(HttpServletRequest request) {
		return new ModelAndView("status");
		}
	
	@GetMapping(value="/about")
	public ModelAndView about(HttpServletRequest request) {
		return new ModelAndView("about");
	}
	
	@GetMapping(value="/faq")
	public ModelAndView faq(HttpServletRequest request) {
		return new ModelAndView("faq");
	}
	
	@GetMapping(value="/stepbystep")
	public ModelAndView stepbystep(HttpServletRequest request) {
		return new ModelAndView("stepbystep");
	}

	@PostMapping(value="/sendOnToology")
	public ResponseEntity<?> send(@RequestParam("ontology") MultipartFile file,
			@RequestParam("id") String id,
							HttpServletRequest request) {
		
			try {
				OnToologyService.saveOntology(file, id);
				
				HttpHeaders responseHeaders = new HttpHeaders();
				responseHeaders.setAccessControlAllowOrigin("*");;
				
				return new ResponseEntity<String>("ok", responseHeaders, HttpStatus.OK);

			} catch (Exception e) {
				HttpHeaders responseHeaders = new HttpHeaders();
				responseHeaders.setAccessControlAllowOrigin("*");;
				LOG.warning(e.getLocalizedMessage());
				
				return new ResponseEntity<String>("Not an ontology", responseHeaders, HttpStatus.INTERNAL_SERVER_ERROR);

			}
	}
	
	@PostMapping(value="/sendTest")
	public ResponseEntity<?> sendTest(@RequestParam("test") MultipartFile test,
			@RequestParam("id") String id, @RequestParam("nOnt") String nOnt,
							HttpServletRequest request) {
		
			try {
				OnToologyService.saveTest(test, id, nOnt);
				HttpHeaders responseHeaders = new HttpHeaders();
				responseHeaders.setAccessControlAllowOrigin("*");;
				
				return new ResponseEntity<String>("ok", responseHeaders, HttpStatus.OK);

			} catch (IOException e) {
				HttpHeaders responseHeaders = new HttpHeaders();
				responseHeaders.setAccessControlAllowOrigin("*");;
				LOG.warning(e.getLocalizedMessage());

				return new ResponseEntity<String>("Not a test", responseHeaders, HttpStatus.INTERNAL_SERVER_ERROR);

			}
		
		
	}
	
	@GetMapping(value = "/widoco/{idClient}/{nOnt}/{originalName}")
	public ResponseEntity<?> widoco(@PathVariable String idClient, @PathVariable String nOnt, @PathVariable String originalName, HttpServletRequest request){

		try {
			OnToologyService.widoco(idClient, nOnt, originalName);
			
			HttpHeaders responseHeaders = new HttpHeaders();
			responseHeaders.setAccessControlAllowOrigin("*");
			
			return new ResponseEntity<String>("OK; Widoco generated", responseHeaders, HttpStatus.OK);

		} catch (Exception e) {
			LOG.warning(e.getLocalizedMessage());

			Path toRemove = Paths.get("tmp//ontologies//" + idClient + "//OnToology//"+ nOnt+"-"+originalName +"//widoco");
			try {
				FileUtils.deleteDirectory(toRemove.toFile());
			} catch (IOException e1) {
				LOG.info("The folder " + toRemove.toString() + " has encounter a problem to be removed");
			}
			
			HttpHeaders responseHeaders = new HttpHeaders();
			responseHeaders.setAccessControlAllowOrigin("*");
			
			return new ResponseEntity<String>("Something went wrong", responseHeaders, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@GetMapping(value = "/ar2dtool/{idClient}/{nOnt}/{originalName}")
	public ResponseEntity<?> ar2dtool(@PathVariable String idClient, @PathVariable String nOnt, @PathVariable String originalName, HttpServletRequest request){

		try {
			OnToologyService.ar2dtool(idClient, nOnt, originalName);
			
			HttpHeaders responseHeaders = new HttpHeaders();
			responseHeaders.setAccessControlAllowOrigin("*");
			
			return new ResponseEntity<String>("OK; AR2DTOOL generated", responseHeaders, HttpStatus.OK);

		} catch (Exception e) {
			LOG.warning(e.getLocalizedMessage());

			Path toRemove = Paths.get("tmp//ontologies//" + idClient + "//OnToology//"+ nOnt+"-"+originalName +"//ar2dtool");
			try {
				FileUtils.deleteDirectory(toRemove.toFile());
			} catch (IOException e1) {
				LOG.info("The folder " + toRemove.toString() + " has encounter a problem to be removed");
			}
			
			HttpHeaders responseHeaders = new HttpHeaders();
			responseHeaders.setAccessControlAllowOrigin("*");
			
			return new ResponseEntity<String>("Something went wrong", responseHeaders, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@GetMapping(value = "/themis/{idClient}/{nOnt}/{originalName}")
	public ResponseEntity<?> themis(@PathVariable String idClient, @PathVariable String nOnt, @PathVariable String originalName, HttpServletRequest request){

		try {
			OnToologyService.themis(idClient, nOnt, originalName);
			
			HttpHeaders responseHeaders = new HttpHeaders();
			responseHeaders.setAccessControlAllowOrigin("*");
			
			return new ResponseEntity<String>("OK; Themis generated", responseHeaders, HttpStatus.OK);

		} catch (Exception e) {
			LOG.warning(e.getLocalizedMessage());

			Path toRemove = Paths.get("tmp//ontologies//" + idClient + "//OnToology//"+ nOnt+"-"+originalName +"//themis");
			try {
				FileUtils.deleteDirectory(toRemove.toFile());
			} catch (IOException e1) {
				LOG.info("The folder " + toRemove.toString() + " has encounter a problem to be removed");
			}
			
			HttpHeaders responseHeaders = new HttpHeaders();
			responseHeaders.setAccessControlAllowOrigin("*");
			
			return new ResponseEntity<String>("Something went wrong", responseHeaders, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@GetMapping(value = "/astrea/{idClient}/{nOnt}/{originalName}")
	public ResponseEntity<?> astrea(@PathVariable String idClient, @PathVariable String nOnt, @PathVariable String originalName, HttpServletRequest request){

		try {
			OnToologyService.astrea(idClient, nOnt, originalName);
			
			HttpHeaders responseHeaders = new HttpHeaders();
			responseHeaders.setAccessControlAllowOrigin("*");
			
			return new ResponseEntity<String>("OK; Astrea generated", responseHeaders, HttpStatus.OK);

		} catch (Exception e) {
			LOG.warning(e.getLocalizedMessage());

			Path toRemove = Paths.get("tmp//ontologies//" + idClient + "//OnToology//"+ nOnt+"-"+originalName +"//astrea");
			try {
				FileUtils.deleteDirectory(toRemove.toFile());
			} catch (IOException e1) {
				LOG.info("The folder " + toRemove.toString() + " has encounter a problem to be removed");
			}
			
			HttpHeaders responseHeaders = new HttpHeaders();
			responseHeaders.setAccessControlAllowOrigin("*");
			
			return new ResponseEntity<String>("Something went wrong", responseHeaders, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@GetMapping(value = "/oops/{idClient}/{nOnt}/{originalName}")
	public ResponseEntity<?> oops(@PathVariable String idClient, @PathVariable String nOnt, @PathVariable String originalName, HttpServletRequest request){

		try {
			OnToologyService.oops(idClient, nOnt, originalName);
			
			HttpHeaders responseHeaders = new HttpHeaders();
			responseHeaders.setAccessControlAllowOrigin("*");
			
			return new ResponseEntity<String>("OK; Oops generated", responseHeaders, HttpStatus.OK);

		} catch (Exception e) {
			LOG.warning(e.getLocalizedMessage());

			Path toRemove = Paths.get("tmp//ontologies//" + idClient + "//OnToology//"+ nOnt+"-"+originalName +"//oops");
			try {
				FileUtils.deleteDirectory(toRemove.toFile());
			} catch (IOException e1) {
				LOG.info("The folder " + toRemove.toString() + " has encounter a problem to be removed");
			}
			
			HttpHeaders responseHeaders = new HttpHeaders();
			responseHeaders.setAccessControlAllowOrigin("*");
			
			return new ResponseEntity<String>("Something went wrong", responseHeaders, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@GetMapping(value="/zip/{idClient}", produces="application/zip")
	public ResponseEntity<String> zip(@PathVariable String idClient, HttpServletResponse response) throws IOException {

		try {
			OnToologyService.zipDirectory(idClient);
			
			HttpHeaders responseHeaders = new HttpHeaders();
			responseHeaders.setAccessControlAllowOrigin("*");
			
			return new ResponseEntity<String>("OK; ZIP generated", responseHeaders, HttpStatus.OK);

		} catch (IOException e) {
			LOG.warning(e.getLocalizedMessage());

			HttpHeaders responseHeaders = new HttpHeaders();
			responseHeaders.setAccessControlAllowOrigin("*");
			return new ResponseEntity<String>("Something went wrong", responseHeaders, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(value="/download/{id}", produces="application/zip")
	public void download(@PathVariable String id, HttpServletResponse response) throws IOException {

	    
        try {
        	response.setStatus(HttpServletResponse.SC_OK);
    	    
    	    response.addHeader("Content-Disposition", "attachment; filename=\"OnToology.zip\"");

    	    Path zip = Paths.get("tmp//ontologies//" + id + "//OnToology.zip");

            InputStream is = new FileInputStream(zip.toFile());
            
            IOUtils.copy(is, response.getOutputStream());
 
            response.flushBuffer();
            
		} catch (IOException e) {
			LOG.warning(e.getLocalizedMessage());

        	response.setStatus(HttpServletResponse.SC_NOT_FOUND); 
            response.flushBuffer();
		}
    }

}

