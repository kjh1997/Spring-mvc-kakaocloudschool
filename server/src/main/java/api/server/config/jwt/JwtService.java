package api.server.config.jwt;

import api.server.Redis.LoginResponseDto;
import api.server.Redis.RedisService;
import api.server.config.jwt.auth.UserPrincipal;
import api.server.service.UserService;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.TokenExpiredException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.stereotype.Service;

import java.time.Duration;


@Service
@RequiredArgsConstructor
public class JwtService {
    private final UserService userService;
    private final RedisService redisService;
    private final TokenGenerator tokenGenerator;


    public String tokenRefresh(LoginResponseDto loginResponseDto) {
        UserPrincipal userPrincipal = null;
        String refreshToken = loginResponseDto.getRefreshToken().split(" ")[1];
        try {
            JWT.require(Algorithm.HMAC512(JwtProperties.SECRET)).build().verify(refreshToken); // refreshToken verify
        } catch (TokenExpiredException e) {
            System.out.println("로그인이 필요합니다.");
            return "로그인이 필요합니다.";
        }

        String username = "";
        String accessToken = redisService.getValues(refreshToken);
        System.out.println("accessToken | " + accessToken);
        try {
            JWT.require(Algorithm.HMAC512(JwtProperties.SECRET)).build().verify(accessToken); // 엑세스 토큰이 유요한지 점검
            return "accessToken이 만료안됨.";
        } catch (TokenExpiredException e) {
            Duration duration = Duration.ofSeconds(JwtProperties.EXPIRATION_TIME_Refresh);
            username = JWT.require(Algorithm.HMAC512(JwtProperties.SECRET)).build().verify(refreshToken).getClaim("username").asString();
            userPrincipal = new UserPrincipal(userService.getUserByName(username));
            String newAccessToken = tokenGenerator.generationToken(userPrincipal, JwtProperties.EXPIRATION_TIME_Access);  // 만료되었으면, 토큰을 재발급시킨다.
            redisService.setValues(refreshToken, newAccessToken, duration);
            System.out.println("토큰 재발급 완료하였음.");
            return newAccessToken;
        } catch (InternalAuthenticationServiceException e) {
            System.out.println(".");
            return "인증 실패";
        }
    }
}
