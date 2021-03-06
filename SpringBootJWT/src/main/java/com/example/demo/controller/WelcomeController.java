package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.AuthRequest;
import com.example.demo.util.jwtUtil;

@RestController
public class WelcomeController {
	
	@Autowired
	private jwtUtil jwtUtil;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	
	//To call GET  endpoint pass the Autorization header with the token generated in post call.
	@GetMapping("/")
	public String welcome() {
		
		return "Welcome to Json web token practice";
	}

	//Hit the post endpoint  : http://localhost:8080/authenticate
	 //and  pass the body with username and password as json. 
	@PostMapping("/authenticate")
	public String generateToken(@RequestBody AuthRequest authrequest) throws Exception {
       try {
			
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authrequest.getUsername(), authrequest.getPassword()));
		}catch(Exception ex) {
			 throw new Exception("Inavlid username and password Exception.");
		}
		
		return jwtUtil.generateToken(authrequest.getUsername());
		
	}
}
