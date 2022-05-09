package com.example.tinkofftradingrobot.config.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

public class ApiAuthenticationFilter extends OncePerRequestFilter {

    private final ApiAuthenticationProvider apiAuthenticationProvider;
    private final String[] paths = {"/api/user/create"};

    public ApiAuthenticationFilter(ApiAuthenticationProvider apiAuthenticationProvider) {
        this.apiAuthenticationProvider = apiAuthenticationProvider;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String xAuth = request.getHeader("X-Authorization");

        var apiAuthenticationToken = new ApiAuthenticationToken(xAuth);
        Authentication authentication = apiAuthenticationProvider.authenticate(apiAuthenticationToken);
        if(!authentication.isAuthenticated()){
            throw new SecurityException();
        }
        SecurityContextHolder.getContext().setAuthentication(authentication);
        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
//        return false;
        return Arrays.asList(paths).contains(request.getServletPath());
    }
}
