package com.example.detail.filters;

import com.example.detail.exceptions.NotAllowException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@WebFilter(urlPatterns={"/exp/*"})
public class PermissionFilter implements Filter {


    @Autowired
    @Qualifier("handlerExceptionResolver")
    private HandlerExceptionResolver resolver;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("[PermissionFilter]init...");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        String token = request.getHeader("token");
        if (!"111111".equals(token)) {
            log.warn("[PermissionFilter]token error");
            // 过滤器中抛出异常，无法被RestControllerAdvice捕获到，因为过滤器操作发生在doDispatch之前
            // throw new NotAllowException();
            resolver.resolveException(request, (HttpServletResponse)servletResponse, null, new NotAllowException());
            return;
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        log.info("[PermissionFilter]destroy...");
    }
}
