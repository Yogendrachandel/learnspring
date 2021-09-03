
package com.example.demo.securityconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration

@EnableWebSecurity
public class AppSecuityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService; // This is the interface so we need to create
	// Class that implements this Interafce.

	@Bean
	public AuthenticationProvider getAuth() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(userDetailsService);
		provider.setPasswordEncoder(new BCryptPasswordEncoder());// use password encoder
		return provider;
	}

		// This method is used to provide our custom login page not by spring default	login page.
		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http.csrf().disable()
			.authorizeRequests().antMatchers("/login").permitAll().anyRequest().authenticated()
			.and().
		    formLogin().loginPage("/login").permitAll().and().logout().invalidateHttpSession(true)
			.clearAuthentication(true).logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
			.logoutSuccessUrl("/logout-sucess").permitAll();
		}

}
