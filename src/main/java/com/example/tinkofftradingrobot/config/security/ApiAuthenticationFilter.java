package com.example.tinkofftradingrobot.config.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ApiAuthenticationFilter extends OncePerRequestFilter {

    private final ApiAuthenticationProvider apiAuthenticationProvider;

    public ApiAuthenticationFilter(ApiAuthenticationProvider apiAuthenticationProvider) {
        this.apiAuthenticationProvider = apiAuthenticationProvider;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String xAuth = request.getHeader("X-Authorization");

        // validate the value in xAuth
        if(idk(xAuth) == false){
            throw new SecurityException();
        }

        // The token is 'valid' so magically get a user id from it
        Long id = getUserIdFromToken(xAuth);

        // Create our Authentication and let Spring know about it
        Authentication auth = new ApiAuthenticationToken(id);
        SecurityContextHolder.getContext().setAuthentication(auth);

        filterChain.doFilter(request, response);
    }

//    @Override
//    protected boolean shouldNotFilter(HttpServletRequest request) {
//        return false;
//    }
}
