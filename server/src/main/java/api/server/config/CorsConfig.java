package api.server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

//이 어노테이션을 단 클래스는 빈 설정을 담당하는 클래스가 된다.
// 이 클래스 안에서 @Bean 어노테이션이 동봉된 메소드를 선언하면,
// 그 메소드를 통해 스프링 빈을 정의하고 생명주기를 설정하게 된다
@Configuration
public class CorsConfig {
    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedMethod("*");
        config.addAllowedHeader("*");
        config.addAllowedOrigin("*");

        source.registerCorsConfiguration("/api/**", config);
        return new CorsFilter(source);
    }
}
