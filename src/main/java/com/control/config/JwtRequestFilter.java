package com.control.config;


import com.control.service.UserService;
import com.control.utils.JwtTokenUteils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.Base64Utils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Collectors;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    private final JwtTokenUteils jwtTokenUtiels;
    private final UserService userService;

    @Autowired
    public JwtRequestFilter(JwtTokenUteils jwtTokenUtiels, UserService userService) {
        this.jwtTokenUtiels = jwtTokenUtiels;
        this.userService = userService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        String refreshToken = request.getHeader("RefreshToken");
        String username = null;
        String jwt = null;
        System.out.println("ishladi .......");
        System.out.println(authHeader);
        if (!authHeader.startsWith("Bearer ")) {
            System.out.println("hed++");
            String encodedCredentials = authHeader.substring(6).trim();
            String decodedCredentials = new String(Base64Utils.decodeFromString(encodedCredentials));

            String[] credentials = decodedCredentials.split(":", 2);
            System.out.println(credentials[0]);
            UserDetails userDetails = userService.loadUserByUsername(credentials[0]);
            String s = jwtTokenUtiels.generateToken(userDetails);
            String ref = jwtTokenUtiels.refreshToken(s);
            response.addHeader("Authorization", "Bearer " + s);
            response.addHeader("RefreshToken", "Bearer " + ref);
        }
        System.out.println(jwtTokenUtiels.checkExpiredDate(authHeader.substring(7)));
        System.out.println(!jwtTokenUtiels.checkExpiredDate(refreshToken));
        System.out.println(jwtTokenUtiels.validateTokens(authHeader, refreshToken));
        if (jwtTokenUtiels.checkExpiredDate(authHeader.substring(7)) &&
                !jwtTokenUtiels.checkExpiredDate(refreshToken) &&
                jwtTokenUtiels.validateTokens(authHeader, refreshToken)) {
            System.out.println("if ishladi////");
            String username1 = jwtTokenUtiels.getUsername(authHeader.substring(7));
            UserDetails userDetails = userService.loadUserByUsername(username1);
            String s = jwtTokenUtiels.generateToken(userDetails);
            String s1 = jwtTokenUtiels.refreshToken(s);
            response.addHeader(s, s1);
        }
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            jwt = authHeader.substring(7);
            username = jwtTokenUtiels.getUsername(jwt);
        }
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                    username,
                    null,
                    jwtTokenUtiels.getRoles(jwt).stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList())
            );
            SecurityContextHolder.getContext().setAuthentication(token);
        }

        response.addHeader("token", jwtTokenUtiels.generateToken(userService.loadUserByUsername(jwtTokenUtiels.getUsername(jwt))));
        filterChain.doFilter(request, response);
    }
}
