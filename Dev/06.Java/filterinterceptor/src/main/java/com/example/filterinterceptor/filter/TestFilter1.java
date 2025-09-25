package com.example.filterinterceptor.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Slf4j
@WebFilter(urlPatterns = {"/hello"})
public class TestFilter1 implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("[필터1] 요청 자원 수행 전");
        filterChain.doFilter(servletRequest, servletResponse);
        log.info("[필터1] 요청 자원 수행 후");
    }
}
