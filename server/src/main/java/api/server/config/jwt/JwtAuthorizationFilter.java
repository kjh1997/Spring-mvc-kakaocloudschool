package api.server.config.jwt;

import api.server.config.jwt.auth.UserPrincipal;
import api.server.domain.User;
import api.server.domain.repository.UserInterFace;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.TokenExpiredException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Date;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    private UserInterFace userInterface;

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager, UserInterFace userInterFace) {
        super(authenticationManager);
        System.out.println("JwtAuthorizationFilter create");
        this.userInterface = userInterFace;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        String header = request.getHeader(JwtProperties.HEADER_STRING);
        System.out.println("doFilterInternal : start " );
        if(header == null || !header.startsWith(JwtProperties.TOKEN_PREFIX)) {
            chain.doFilter(request, response);
            return;
        }
        System.out.println("header : "+header);
        String token = request.getHeader(JwtProperties.HEADER_STRING)
                .replace(JwtProperties.TOKEN_PREFIX, "");
        String username = null;
        // 토큰 검증 (이게 인증이기 때문에 AuthenticationManager도 필요 없음)
        // 내가 SecurityContext에 집적접근해서 세션을 만들때 자동으로 UserDetailsService에 있는 loadByUsername이 호출됨.
        try {
            username = JWT.require(Algorithm.HMAC512(JwtProperties.SECRET)).build().verify(token).getClaim("username").asString();

        } catch (TokenExpiredException e) {
            response.addHeader("test", "your token is expired, you need token generation!!");
            chain.doFilter(request, response);

            System.out.println("you need token generation!!");
            return;
        } catch (InternalAuthenticationServiceException e) {
            response.addHeader("test", "you need login");
            chain.doFilter(request, response);
            return;
        }

//        Date date = JWT.require(Algorithm.HMAC512(JwtProperties.SECRET)).build().verify(token).getExpiresAt();
//        System.out.println("jwt data : " + date);
//        String payload = JWT.require(Algorithm.HMAC512(JwtProperties.SECRET)).build().verify(token).getPayload();
//        System.out.println("payload : " + payload.getBytes(StandardCharsets.UTF_8) );

//        response.addHeader("test", "토큰을 재발급 받으시오.");

        if(username != null) {
            User user = userInterface.findByusername(username);

            // 인증은 토큰 검증시 끝. 인증을 하기 위해서가 아닌 스프링 시큐리티가 수행해주는 권한 처리를 위해
            // 아래와 같이 토큰을 만들어서 Authentication 체를 강제로 만들고 그걸 세션에 저장!
            UserPrincipal principalDetails = new UserPrincipal(user);
            Authentication authentication =
                    new UsernamePasswordAuthenticationToken(
                            principalDetails, //나중에 컨트롤러에서 DI해서 쓸 때 사용하기 편함.
                            null, // 패스워드는 모르니까 null 처리, 어차피 지금 인증하는게 아니니까!!
                            principalDetails.getAuthorities());
            // 강제로 시큐리티의 세션에 접근하여 값 저장
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        chain.doFilter(request, response);
    }
}