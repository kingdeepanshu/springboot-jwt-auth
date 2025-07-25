package com.devkaran.user_authentication;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {
    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain fl) throws ServletException, IOException {
        String path = req.getServletPath();
        if(path.equals("/auth/login")){
            fl.doFilter(req, res);
            return;
        }

        if(path.equals("/auth/register")){
            fl.doFilter(req, res);
            return;
        }

        if(path.equals("/")){
            fl.doFilter(req, res);
            return;
        }

        String authHeader = req.getHeader("Authorization");
        if(authHeader != null && authHeader.toLowerCase().startsWith("bearer ")){
            String token = authHeader.substring(7);
            String username = jwtService.extractUsername(token);

            if(username != null && SecurityContextHolder.getContext().getAuthentication() == null){
                var userDetails = userDetailsService.loadUserByUsername(username);
                List<String> roles = jwtService.getRoles(token);

                var authorities = roles.stream()
                        .map(role -> new SimpleGrantedAuthority(role))
                        .toList();

                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, authorities);
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(req));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        fl.doFilter(req, res);
    }
}
