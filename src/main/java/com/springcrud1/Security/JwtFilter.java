package com.springcrud1.Security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;

@Component
public  class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    // ✅ ADD THIS METHOD
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String path = request.getServletPath();
        return path.startsWith("/auth/");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain)
              throws ServletException, IOException {
        System.out.println("JWT INTERCEPTOR CALLED");
        String authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {

            String token = authHeader.substring(7);

            try {
                System.out.println("Token: " + token);

                String username = jwtUtil.extractUsername(token);
                System.out.println("Username: " + username);

                boolean isValid = jwtUtil.validateToken(token);
                System.out.println("Is Token Valid: " + isValid);

                if (jwtUtil.validateToken(token)) {

                    UsernamePasswordAuthenticationToken auth =
                            new UsernamePasswordAuthenticationToken(
                                    username, null, new ArrayList<>());
                    System.out.println("Setting authentication...");
                    SecurityContextHolder.getContext().setAuthentication(auth);
                }

            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Token Should be Expired or Invalid");
            }
        }

        chain.doFilter(request, response);
    }
}