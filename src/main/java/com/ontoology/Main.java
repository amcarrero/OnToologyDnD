package main.java.com.ontoology;

import main.java.com.ontoology.util.Init;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

@Controller
@SpringBootApplication
public class Main {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(Main.class, args);

	}

	@RequestMapping("/")
	public String welcome() {
	     return "index";
	 }
}
