package com.a88.filter;


import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;w

import java.io.IOException;

@WebFilter(urlPatterns = "/*")
public class demoFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("init is implemented");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("check request");
        // 放行
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        System.out.println("destroy is implemented");
    }
}
