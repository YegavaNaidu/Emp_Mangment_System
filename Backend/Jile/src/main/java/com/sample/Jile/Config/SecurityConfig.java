package com.sample.Jile.Config;

import com.sample.Jile.security.JWTAuthenticationEntryPoint;
import com.sample.Jile.security.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {


    @Autowired
    private JwtAuthenticationFilter filter;
    @Autowired
    private JWTAuthenticationEntryPoint point;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.csrf(csrf -> csrf.disable())
                .cors( cors -> cors.disable())
                .authorizeHttpRequests(auth ->auth.requestMatchers("/test").authenticated().
                        requestMatchers("/register").permitAll().anyRequest()
                        .authenticated()).exceptionHandling( ex ->ex.authenticationEntryPoint(point))
                .sessionManagement( ss -> ss.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        ;
        http.addFilterBefore( filter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

}
