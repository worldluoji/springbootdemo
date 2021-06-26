package com.example.detail.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

// import com.google.common.io.ByteStreams;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class ReadBodyFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        //400: Required request body is missing
        // String requestBody = new String(ByteStreams.toByteArray(request.getInputStream()));
        // log.info("print request body in filter:" + requestBody);
        log.info("enter in ReadBodyFilter");
        chain.doFilter(request, response);
    }
    
}
