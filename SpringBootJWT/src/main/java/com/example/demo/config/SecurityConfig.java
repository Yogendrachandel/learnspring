package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.demo.jwtFilter.JwtFilter;
import com.example.demo.services.CustomUserDetailService;

@Configuration
@EnableWebSecurity(debug=true)//to enabled debugging in spring security purpose only, in real production env never set this (debug=true)
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	/*
	 * Read this spring security with JWT :
	 *  1)
	 * https://www.toptal.com/spring/spring-security-tutorial
	 *  2)
	 * https://www.javainuse.com/spring/boot-jwt
	 */
	
	@Autowired
	private JwtFilter jwtFilter;
	
	
	@Autowired
	private CustomUserDetailService userDetailService;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// configure AuthenticationManager so that it knows from where to load
		// user for matching credentials
		
		auth.userDetailsService(userDetailService);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests().antMatchers("/authenticate").permitAll()
		.anyRequest().authenticated().and().exceptionHandling()
		.and().sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS);// To make application session stateless so that  no token like JSESSIONID or other  CSRF token store will not store in session or cookie of Application
		//poilicy of SessionCreationPolicy- ALWAYS,	NEVER,	IF_REQUIRED,STATELESS
		http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);// Register our custom filter ,here we are telling our custom filter to call  before  of mention(UsernamePasswordAuthenticationFilter)  spring filter.
	}
	
	
	
	//Use in Controller class with @Autowired annotaion
	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		// TODO Auto-generated method stub
		return super.authenticationManagerBean();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}

}
