package com.larry.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
@EnableMethodSecurity
@EnableWebSecurity(debug=true) //1. enable Spring security's web security support for Spring MVC
public class SecurityConfig {
    @Bean
    public UserDetailsService userDetailsService() throws Exception{
        // ensure the passwords are encoded properly
        User.UserBuilder users = User.withDefaultPasswordEncoder();
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(users.username("user").password("password").roles("USER").build());
        manager.createUser(users.username("admin").password("password").roles("USER","ADMIN").build());
        return manager;
    }
    //2. The SecurityFilterChain bean defines with URL paths should be secured and which should not
    // Since new version of Spring security, WebSecurityConfigurerAdapter was deprecated
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        /*
        Below configuration will result in the folowing Filter ordering:
        1. CsrfFilter: is invoked to protect against CSRF attacks
        2. UsernamePasswordAuthenticationFilter: is invoked to authenticate the request
        3. AuthorizationFilter: is invoked to authorize the request
         */
       http
//               .csrf().disable() //csrf protection is enabled by default in spring security， it is not good practise to disable it
               .csrf(csrf -> csrf.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())) //config cookie csrf token repository
               .authorizeHttpRequests(auth -> auth
                       .requestMatchers("/login").permitAll()
                       //.requestMatchers("/public/api/**", "/error", "/api/index/**").permitAll()
                       .anyRequest().authenticated())
               .formLogin(Customizer.withDefaults());
       return http.build();
    }
}
