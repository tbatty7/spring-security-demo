package com.battybuilds.springsecuritydemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // Authentication
        auth.inMemoryAuthentication()
        .withUser("admin")
        .password("admintim")
        .roles("ADMIN")
        .and()
        .withUser("user")
        .password("awesome")
        .roles("USER");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // Authorization
        http.authorizeRequests().antMatchers("/**").hasRole("ADMIN")
        .and().formLogin();
    }

    @Bean
    public PasswordEncoder myPasswordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}
