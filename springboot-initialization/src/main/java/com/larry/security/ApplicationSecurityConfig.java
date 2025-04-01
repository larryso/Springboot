//package com.larry.security;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//
///**
// * Deprecated
// *
// * begin from Spring security 5.7.0-M2 deprecated WebSecurityConfigureAdapter
// */
////@Configuration
////@EnableWebSecurity
////@Deprecated
//public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {
//    @Override
//    protected void configure(HttpSecurity http) throws Exception{
//        http.authorizeRequests()
//                .antMatchers(HttpMethod.GET, "/api/v1/**").permitAll()
//                .anyRequest().authenticated()
//                .and().csrf().disable()
//                .cors();
//    }
//}
