package com.battybuilds.springsecuritydemo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource datasource; // Spring automatically configures to h2 database as default

//    This is for Basic In-Memory authentication
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        // Authentication
//        auth.inMemoryAuthentication()
//        .withUser("admin")
//        .password("admintim")
//        .roles("ADMIN")
//        .and()
//        .withUser("user")
//        .password("awesome")
//        .roles("USER");
//    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .dataSource(datasource);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // Authorization
        http.authorizeRequests()
            .antMatchers("/manage").hasRole("ADMIN")
            .antMatchers("/check").hasAnyRole("ADMIN", "USER")
            .antMatchers("/").permitAll()
            .and().formLogin();
    }

    @Bean
    public PasswordEncoder myPasswordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}
