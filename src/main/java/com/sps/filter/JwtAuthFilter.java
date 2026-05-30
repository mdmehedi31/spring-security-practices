package com.sps.filter;

import com.sps.service.CustomUserDetailsService;
import com.sps.service.JWTUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    @Autowired
    private JWTUtils jwtAuthService;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    /* TODO
    * 1. Get authorization header
    * 2. Check header is present or not
    * 3. Extract the token from the Header
    * */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        // 1
        String authHeader = request.getHeader("Authorization");
        String token= null;
        String userName = null;
        // 2
        if(authHeader !=null && authHeader.startsWith("Bearer ")) {
            // 3
           token = authHeader.substring(7);
            userName= jwtAuthService.getUserNameFromToken(token);
        }
        // If user already authenticated then it should store in the security context
        if(userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            // TODOis fetch from db
             UserDetails  userDetails =this.customUserDetailsService.loadUserByUsername(userName);
             if(userDetails !=null) {
                 // Validate the token
                 boolean isValid = jwtAuthService.validateToken(userName, userDetails, token);
                 if (isValid) {
                     UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails,
                             null, userDetails.getAuthorities());
                     authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                     // If valid then need to add those in the security context holder
                     SecurityContextHolder.getContext().setAuthentication(authToken);
                 }
             }
        }
        filterChain.doFilter(request, response);
    }
}
