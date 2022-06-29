package api.server.config.jwt;

//import api.server.config.auth.PrincipalDetails;
import api.server.config.dto.LoginDataDTO;
import api.server.config.jwt.auth.UserPrincipal;
import api.server.domain.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@RequiredArgsConstructor

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        // attemptAuthentication 메서드는 HTTP Request가 처음으로 들어오는 메서드
        //attemptAuthentication 메서드에서는 Request Body에 있는 데이터를 DTO 객체로 변환한 후 Provider Manager의 authentication 메서드에 전달하게 된다.
        //authentication 메서드의 결과가 성공이라면 인증된 객체가 successfulAuthentication 메서드로 돌아오고, 발행된 토큰과 같은 적절한 데이터를 포함한 HTTP Response를 돌려주면 된다.
        //authentication 메서드의 결과가 실패라면 인자로 넘어온 예외를 활용해 사용자에게 적절한데이터를 보여줘 이후 프로세스를 진행할 수 있도록 하면 된다.
        System.out.println("authentication start");
        ObjectMapper om = new ObjectMapper();
        LoginDataDTO loginDataDTO = null;
        try {
            loginDataDTO = om.readValue(request.getInputStream(), LoginDataDTO.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("login data parsing : " + loginDataDTO);

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginDataDTO.getUsername(), loginDataDTO.getPassword());

        System.out.println("login token generation : " + authenticationToken);

        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        System.out.println("token generation complete : " + authentication);
        // authenticate() 함수가 호출 되면 인증 프로바이더가 유저 디테일 서비스의
        // loadUserByUsername(토큰의 첫번째 파라메터) 를 호출하고
        // UserDetails를 리턴받아서 토큰의 두번째 파라메터(credential)과
        // UserDetails(DB값)의 getPassword()함수로 비교해서 동일하면
        // Authentication 객체를 만들어서 필터체인으로 리턴해준다.

        // Tip: 인증 프로바이더의 디폴트 서비스는 UserDetailsService 타입
        // Tip: 인증 프로바이더의 디폴트 암호화 방식은 BCryptPasswordEncoder
        // 결론은 인증 프로바이더에게 알려줄 필요가 없음.

        // Authentication : Spring Security에서 한 유저의 인증 정보를 가지고 있는 객체
        System.out.println("================print test================");
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        System.out.println("Authentication : "+userPrincipal.getUser().getUsername());
        return authentication;
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult)
            throws IOException, ServletException {
        UserPrincipal userPrincipal = (UserPrincipal) authResult.getPrincipal();
        String jwtToken = JWT.create()
                .withSubject(userPrincipal.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + JwtProperties.EXPIRATION_TIME))
                .withClaim("id", userPrincipal.getUser().getId())
                .withClaim("username", userPrincipal.getUser().getUsername())
                .sign(Algorithm.HMAC512(JwtProperties.SECRET));
        response.addHeader(JwtProperties.HEADER_STRING, JwtProperties.TOKEN_PREFIX + jwtToken);
        System.out.println("jwtToken : " +  jwtToken);

    }
}
