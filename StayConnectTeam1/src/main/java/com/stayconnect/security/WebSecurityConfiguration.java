package com.stayconnect.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;


@Configuration
public class WebSecurityConfiguration {
	@Autowired 
	private DataSource dataSource;
	
	@Autowired
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().dataSource(dataSource)
			.usersByUsernameQuery("SELECT email, password, active FROM user_account " + " WHERE email=?")
			.authoritiesByUsernameQuery("SELECT email, authority FROM user_account " + " WHERE email=?");
	}

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests(authorize -> {
            authorize.antMatchers("/login", "/account/activation").permitAll();
            authorize.antMatchers("/registration","/webhome").permitAll();
            authorize.antMatchers("/add/job").hasAnyRole("STUDENT","ALUMNI","FACULTY");
            authorize.antMatchers("/view/myjobs").hasAnyRole("STUDENT","ALUMNI","FACULTY");
            


            try {
				authorize.anyRequest().authenticated()
				.and()
				   .formLogin().loginPage("/login")
		        .and()
				   .exceptionHandling()
				   		.accessDeniedPage("/403");
			} catch (Exception e) {
				e.printStackTrace();
			}
        });
        return http.build();	
    }
    
    @Bean
    public PasswordEncoder passwordEncoder() {
    	return new BCryptPasswordEncoder();
    }
}
