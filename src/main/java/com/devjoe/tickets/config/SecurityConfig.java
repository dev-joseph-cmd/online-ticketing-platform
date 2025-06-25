package com.devjoe.tickets.config;


import com.devjoe.tickets.filters.UserProvisioningFilters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.server.resource.web.authentication.BearerTokenAuthenticationFilter;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity, UserProvisioningFilters userProvisioningFilters) throws Exception{

        httpSecurity
                .authorizeHttpRequests(authorizationManagerRequestMatcherRegistry ->
                        authorizationManagerRequestMatcherRegistry.anyRequest().authenticated())
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement( session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                        ).oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults())


                        ).addFilterAfter(userProvisioningFilters, BearerTokenAuthenticationFilter.class);


        return httpSecurity.build();



    }
}
