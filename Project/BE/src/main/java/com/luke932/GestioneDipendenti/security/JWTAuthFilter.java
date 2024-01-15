package com.luke932.GestioneDipendenti.security;

import com.luke932.GestioneDipendenti.entity.Utenti;
import com.luke932.GestioneDipendenti.service.UtentiService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.UUID;

@Component
public class JWTAuthFilter extends OncePerRequestFilter {
    private final UtentiService utentiS;
    private final JWTTools jwt;
    @Autowired
    public JWTAuthFilter(UtentiService utentiS,JWTTools jwt){
        this.utentiS=utentiS;
        this.jwt=jwt;
    }
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            System.out.println("TOKEN -------> " + token);

            jwt.verifyToken(token);
            String id = jwt.extractSubject(token);
            Utenti currentUser = utentiS.findById(UUID.fromString(id));

            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(currentUser, null,
                    currentUser.getAuthorities());

            SecurityContextHolder.getContext().setAuthentication(authToken);
        }

        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        System.out.println(request.getServletPath());
        return new AntPathMatcher().match("/auth/**", request.getServletPath());
    }
}
