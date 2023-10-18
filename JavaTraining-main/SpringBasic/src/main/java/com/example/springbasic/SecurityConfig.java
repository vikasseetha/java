package com.example.springbasic;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	/**
	 * This method can access all the api's who has admin access
	 */
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/api/admin/**").hasRole("ADMIN").anyRequest().permitAll().and()
				.httpBasic().and().csrf().disable();
	}
/**
 * Thsi method will provide admin role to user based on username and password
 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("nikhilMengu").password(passwordEncoder().encode("nikhil123")).roles("ADMIN");
	}

	/**
	 * This method is used for encoding the password
	 * @return
	 */
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
