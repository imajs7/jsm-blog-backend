package com.jsmblog.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SuppressWarnings("deprecation")
@EnableWebSecurity
public class AppSecurityConfig extends WebSecurityConfigurerAdapter{

	// settings for authorization
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.csrf().disable();
		
		http
			.authorizeRequests()
			.antMatchers("/auth/**").permitAll()
			.anyRequest().authenticated()
			.and()
			.formLogin()
			.and()
			.httpBasic();
		
	}

	// settings for authentication
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth
			.inMemoryAuthentication()
			.withUser("superadmin").password( passwordEncoder().encode("12345678") ).roles("ADMIN")
			.and()
			.withUser("anukhg").password( passwordEncoder().encode("12345678") ).roles("SUBSCRIBER")
			.and()
			.passwordEncoder( passwordEncoder() );
		
	}

	@Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
	
	

}
