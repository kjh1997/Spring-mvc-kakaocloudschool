package kjh.jwt.config;

import kjh.jwt.filter.Myfilter1;
import kjh.jwt.filter.Myfilter2;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.FilterRegistration;

@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<Myfilter1> filter1(){
        FilterRegistrationBean<Myfilter1> bean = new FilterRegistrationBean<>(new Myfilter1());
        bean.addUrlPatterns("/*");
        bean.setOrder(0);
        return bean;
    }
    @Bean
    public FilterRegistrationBean<Myfilter2> filter2(){
        FilterRegistrationBean<Myfilter2> bean = new FilterRegistrationBean<>(new Myfilter2());
        bean.addUrlPatterns("/*");
        bean.setOrder(1);
        return bean;
    }
}
