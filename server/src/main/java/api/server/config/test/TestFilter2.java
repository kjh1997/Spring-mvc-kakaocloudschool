package api.server.config.test;


import javax.servlet.*;
import java.io.IOException;

public class TestFilter2 implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("filter2");
        chain.doFilter(request, response);
    }
}
