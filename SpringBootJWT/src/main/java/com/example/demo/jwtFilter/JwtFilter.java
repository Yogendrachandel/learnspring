package com.example.demo.jwtFilter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.demo.services.CustomUserDetailService;
import com.example.demo.util.jwtUtil;

@Component
public class JwtFilter  extends OncePerRequestFilter{
    
	@Autowired
	private jwtUtil jwtUtil;
	
	@Autowired
	private CustomUserDetailService customUserDetailService;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		
		System.out.println("Called Filter name -"+filterChain.getClass().getName());
		
		
		
		//Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ5b2dlbmRyYSIsImV4cCI6MTYxMzk0OTA3NiwiaWF0IjoxNjEzOTEzMDc2fQ.Phk3o3Ly8dKY-N-IVP-kCtZsLnf5gPJpG6KU-jJ8ACA
		String authorizationHeader= request.getHeader("Authorization");
		String token =null;
		String username=null;
		
		if(authorizationHeader!=null && authorizationHeader.startsWith("Bearer")) {
			 token =authorizationHeader.substring(7);
			//extract username
			 username=jwtUtil.extractUsername(token);
		}
			 
			 
		 if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null) {
			 System.out.println("#### Going to call user record from DB to validate the token ####");
			 
			 UserDetails userDetails= customUserDetailService.loadUserByUsername(username);
			 
			 //Validating the user details stored in token and from database record.
			 if (jwtUtil.validateToken(token, userDetails)) {

					UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
							userDetails, null, userDetails.getAuthorities());
					
					usernamePasswordAuthenticationToken
							.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					
					//Storing the Authentication object(UsernamePasswordAuthenticationToken extends Authentication) in Spring security context.
					// After setting the Authentication in the context, we specify
					// that the current user is authenticated. So it passes the
					// Spring Security Configurations successfully.
					SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
				}
			 
		 }
			 filterChain.doFilter(request, response);// the next filter in the chain to be invoked, or if the calling filter is the last filter in the chain, causes the resource at the end ofthe chain to be invoked.
		
		
		
	}

}
