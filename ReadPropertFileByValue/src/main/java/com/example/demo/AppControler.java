package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/app")
public class AppControler {
	
	
	@Autowired
	private Environment env; // first way to read property file.
	
	
	 @Value("${app.title}")//second way to read property file.
	 private String appTitle;
	 
	 
	 @Autowired
	 private AppProperties appProperties;
	
	
	@RequestMapping("/test")
	public String test() {
		return " calling test ..";
	}
	
	
	@RequestMapping("/value")
	public String readFromValues() {
		return appTitle;
	}
	
	
	@GetMapping("/property")//read from environment
	public String getPropertyValue(@RequestParam("key") String key) {
		String returnValue = "No value";

		String keyValue = env.getProperty(key);

		if (keyValue != null && !keyValue.isEmpty()) {
			returnValue = keyValue;
		}
		return returnValue;
	}
	
	@RequestMapping("/description")
	public String demo() {
		return appProperties.getDescription();
	}

}
