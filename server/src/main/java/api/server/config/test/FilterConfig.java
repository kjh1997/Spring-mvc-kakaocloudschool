package api.server.config.test;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.logging.Filter;

@Configuration
public class FilterConfig {
    @Bean
    public FilterRegistrationBean<TestFilter1> filter1() {
        FilterRegistrationBean<TestFilter1> bean = new FilterRegistrationBean<>(new TestFilter1());
        bean.addUrlPatterns("/*");
        bean.setOrder(0);
        return bean;
    }

    @Bean
    public FilterRegistrationBean<TestFilter2> filter2() {
        FilterRegistrationBean<TestFilter2> bean = new FilterRegistrationBean<>(new TestFilter2());
        bean.addUrlPatterns("/*");
        bean.setOrder(0);
        return bean;
    }

}
