package com.example.tinkofftradingrobot.config.security;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
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
            try {
                auth = apiAuthenticationProvider.authenticate(new ApiAuthenticationToken(xAuth));
            } catch (UnknownUserException e) {
                response.setStatus(HttpStatus.FORBIDDEN.value());
                response.getWriter().write(convertObjectToJson(e.getMessage()));
                return;
            }
        }
        SecurityContextHolder.getContext().setAuthentication(auth);
        filterChain.doFilter(request, response);
    }

    private String convertObjectToJson(Object object) throws JsonProcessingException {
        if (object == null) {
            return null;
        }
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(object);
    }

//    @Override protected boolean shouldNotFilter(HttpServletRequest request) {
//        return false;
//        return Arrays.asList(paths).contains(request.getServletPath());
//    }
}
