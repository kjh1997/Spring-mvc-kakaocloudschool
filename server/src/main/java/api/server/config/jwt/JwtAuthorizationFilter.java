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
        if(username != null) {
            User user = userInterface.findByusername(username);
            System.out.println(user.getUsername());
            response.addHeader("username", user.getUsername());
            UserPrincipal principalDetails = new UserPrincipal(user);
            Authentication authentication =
                    new UsernamePasswordAuthenticationToken(
                            principalDetails, //나중에 컨트롤러에서 DI해서 쓸 때 사용하기 편함.
                            null, // 패스워드는 모르니까 null 처리, 어차피 지금 인증하는게 아니니까!!
                            principalDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        chain.doFilter(request, response);
    }
}