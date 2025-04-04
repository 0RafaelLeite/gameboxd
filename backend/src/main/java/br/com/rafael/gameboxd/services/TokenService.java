package br.com.rafael.gameboxd.services;

import br.com.rafael.gameboxd.domain.credential.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

@Service
public class TokenService {
    @Value("${api.security.token.secret")
    private String secret;

    public String generateToken(User user) {
        try {
            Algorithm algotithm = Algorithm.HMAC256(secret);
            String token = JWT.create()
                              .withIssuer("gameboxd")
                              .withSubject(user.getUsername())
                              .withExpiresAt(generateExpirationDate())
                              .sign(algotithm);
            return token;
        }catch(JWTCreationException e){
            throw new RuntimeException("Error while generating token: ", e);
        }
    }
    public String validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("gameboxd")
                    .build()
                    .verify(token)
                    .getSubject();
        }catch (JWTVerificationException e){
            return "";
        }
    }

    private Instant generateExpirationDate() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
