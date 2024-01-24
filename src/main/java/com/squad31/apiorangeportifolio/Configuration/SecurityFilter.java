package com.squad31.apiorangeportifolio.Configuration;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class SecurityFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

    }

    private String recoverToken(HttpServletRequest request) {
        //Por padrão o front-end envia o token em um header com nome de Authorization
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null) return null;
        //A estrutura pdrão do token enviado pelo front-end é: Bearer exemplodetoken
        //Aqui estamos retirando o "Bearer" e ficando apenas com o token
        return authHeader.replace("Bearer ", "");
    }
}
