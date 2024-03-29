package com.squad31.apiorangeportifolio.Configuration;

import com.squad31.apiorangeportifolio.Domain.Repository.UserRepository;
import com.squad31.apiorangeportifolio.Domain.Service.TokenService;
import com.squad31.apiorangeportifolio.Utils.GoogleLoginService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.security.GeneralSecurityException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GoogleLoginService googleLoginService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String token = this.recoverToken(request);

        String clientId = request.getHeader("ClientId");


        if (token != null) {
            String email = clientId == null ? tokenService.validateToken(token): googleLoginService.validate(token, clientId);
            UserDetails user = userRepository.findByEmail(email);

            if (user != null) {
                UsernamePasswordAuthenticationToken authenticationDetails = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authenticationDetails);
            }
        }

        filterChain.doFilter(request, response);
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
