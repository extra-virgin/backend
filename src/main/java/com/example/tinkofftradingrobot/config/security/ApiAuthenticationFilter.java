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
    private final String userCreatePath = "/api/user/create";

    public ApiAuthenticationFilter(ApiAuthenticationProvider apiAuthenticationProvider) {
        this.apiAuthenticationProvider = apiAuthenticationProvider;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String xAuth = request.getHeader("X-Authorization");

        String requestPath = request.getServletPath();
        Authentication auth;
        // handle userCreate request
        if (requestPath.equals(userCreatePath)) {
            auth = new ApiAuthenticationToken(xAuth);
            auth.setAuthenticated(true);
            // handle other request
        } else {
            auth = apiAuthenticationProvider.authenticate(new ApiAuthenticationToken(xAuth));
            if (!auth.isAuthenticated()) {
                throw new SecurityException();
            }
        }
        SecurityContextHolder.getContext().setAuthentication(auth);
        filterChain.doFilter(request, response);
    }

//    @Override protected boolean shouldNotFilter(HttpServletRequest request) {
//        return false;
//        return Arrays.asList(paths).contains(request.getServletPath());
//    }
}
