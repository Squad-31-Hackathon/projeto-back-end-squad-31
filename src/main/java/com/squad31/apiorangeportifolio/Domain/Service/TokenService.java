package com.squad31.apiorangeportifolio.Domain.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.squad31.apiorangeportifolio.Domain.Entity.User;
import com.squad31.apiorangeportifolio.Exceptions.BadRequestException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${token.secret}")
    private String TOKEN_SECRET;

    public String generateToken(User user) {

        try {
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);

            return JWT.create()
                    .withIssuer("api-squad-31")
                    .withSubject(user.getEmail())
                    .withClaim("userId", user.getUuid().toString())
                    .withExpiresAt(generateExpirationDate())
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            throw new BadRequestException(exception.getMessage());
        }
    }

    public String validateToken(String token) {

        try {
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            return JWT.require(algorithm)
                    .withIssuer("api-squad-31")
                    .build()
                    .verify(token)
                    .getSubject();

        } catch (JWTVerificationException exception) {
            throw new BadRequestException(exception.getMessage());
        }
    }

    private Instant generateExpirationDate() {
        //Gera um hora a partir da hora atual mais duas horas para o tempo de expiração do token
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
