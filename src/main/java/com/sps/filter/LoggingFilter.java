package com.sps.filter;




import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;


@Configuration
public class LoggingFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        System.out.println("From Custom Filter :: The request url is : "+httpRequest.getRequestURI());
     //   filterChain.doFilter(servletRequest, servletResponse);
    }
}
