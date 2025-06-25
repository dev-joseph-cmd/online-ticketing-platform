package com.devjoe.tickets.filters;


import com.devjoe.tickets.domain.models.User;
import com.devjoe.tickets.domain.repositories.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.UUID;

@RequiredArgsConstructor
@Component
public class UserProvisioningFilters extends OncePerRequestFilter {
    private final UserRepository userRepository;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

      Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

      if(authentication != null && authentication.isAuthenticated() && authentication.getPrincipal() instanceof Jwt jwt){

          UUID keycloakId = UUID.fromString(jwt.getSubject());

          if(!userRepository.existsById(keycloakId)){
              User user = new User();
              user.setId(keycloakId);
              user.setName(jwt.getClaims().get("preferred_username").toString());

              user.setName(jwt.getClaimAsString("email"));
              userRepository.save(user);

          }





      }

      filterChain.doFilter(request, response);

    }
}
