package api.server.config.jwt;

import api.server.config.jwt.auth.UserPrincipal;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@RequiredArgsConstructor
public class TokenGenerator {
    private UserPrincipal userPrincipal;
    public String generationToken(UserPrincipal userPrincipal, int expriation_Time) {
        String jwtToken = JWT.create()
                .withSubject(userPrincipal.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + expriation_Time))
                .withClaim("id", userPrincipal.getUser().getId())
                .withClaim("username", userPrincipal.getUser().getUsername())
                .sign(Algorithm.HMAC512(JwtProperties.SECRET));
        return jwtToken;
    }
}
