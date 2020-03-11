package com.battybuilds.springsecuritydemo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

//    @Autowired
//    private DataSource datasource; // Spring automatically configures to h2 database as default datasource
    @Autowired
    private MyUserDetailsService userDetails;

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

//      This is for JDBC authentication with a local credential database in H2
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.jdbcAuthentication()
//                .dataSource(datasource)
//                .usersByUsernameQuery("select username,password,enabled " +
//                        "from my_users " +
//                        "where username = ?")
//                .authoritiesByUsernameQuery("select username,authority " +
//                        "from authorities " +
//                        "where username = ?");
//    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetails);
    }

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        // Authorization
//        http.csrf().disable()
//                .authorizeRequests()
//            .antMatchers("/manage").hasRole("ADMIN")
//            .antMatchers("/check").hasAnyRole("ADMIN", "USER")
//            .antMatchers("/").permitAll()
//            .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and()
//                .oauth2ResourceServer()
//                .jwt()
//                .decoder(jwtDecoder);
//    }

    @Bean
    public PasswordEncoder myPasswordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}
