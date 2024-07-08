package com.sample.Jile.Config;

import com.sample.Jile.Controller.LoginController;
import com.sample.Jile.Services.CustomUserDetail;
import com.sample.Jile.security.JWTAuthenticationEntryPoint;
import com.sample.Jile.security.JwtAuthenticationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    private Logger logger = LoggerFactory.getLogger(SecurityConfig.class);


    @Autowired
    private JwtAuthenticationFilter filter;
    @Autowired
    private JWTAuthenticationEntryPoint point;



    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        logger.info("yegava----------->" );

        http.csrf(csrf -> csrf.disable())
                //.cors( cors -> cors.disable())
                .authorizeHttpRequests(auth ->auth.requestMatchers("/auth/login").permitAll())
                .authorizeHttpRequests(auth -> auth.requestMatchers("/auth/register","/auth/getuser").authenticated())
                .exceptionHandling( ex ->ex.authenticationEntryPoint(point))
                .sessionManagement( ss -> ss.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore( filter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new CustomUserDetail();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

}
