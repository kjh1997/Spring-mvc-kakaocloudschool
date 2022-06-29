package api.server.config;

import api.server.config.jwt.JwtAuthenticationFilter;
import api.server.config.jwt.JwtAuthorizationFilter;
import api.server.repository.UserInterFace;
import api.server.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.web.filter.CorsFilter;

@EnableWebSecurity  // 스프링 시큐리티 필터가 스프링 필터 체잉네 등록이 됨
@Configuration
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserInterFace userInterFace;
    private final CorsConfig corsConfig;
    private final CorsFilter corsFilter;
//    SessionCreationPolicy.ALWAYS - 스프링시큐리티가항상 세션을 생성
//    SessionCreationPolicy.IF_REQUIRED - 스프링시큐리티가필요시 생성(기본)
//    SessionCreationPolicy.NEVER - 스프링시큐리티가 생성하지않지만, 기존에 존재하면 사용
//    SessionCreationPolicy.STATELESS  - 스프링시큐리티가 생성하지도않고 기존것을 사용하지도 않음
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .addFilter(corsConfig.corsFilter())
            .csrf().disable()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .formLogin().disable()
            .httpBasic().disable()

            .addFilter(new JwtAuthenticationFilter(authenticationManager()))
            .addFilter(new JwtAuthorizationFilter(authenticationManager(), userInterFace))
            .authorizeRequests()
            .antMatchers("/api/v1/user/**")
            .access("hasRole('ROLE_USER') or hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
            .antMatchers("/api/v1/manager/**")
            .access("hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
            .antMatchers("/api/v1/admin/**")
            .access("hasRole('ROLE_ADMIN')")
            .anyRequest().permitAll();
}
}
