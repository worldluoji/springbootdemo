package com.springboot.zuul.demo.simplezuuldemo;

import com.springboot.zuul.demo.simplezuuldemo.filters.ErrorFilter;
import com.springboot.zuul.demo.simplezuuldemo.filters.PostFilter;
import com.springboot.zuul.demo.simplezuuldemo.filters.PreFilter;
import com.springboot.zuul.demo.simplezuuldemo.filters.RouterFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableZuulProxy
public class SimpleZuulDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SimpleZuulDemoApplication.class, args);
    }

    @Bean
    public PreFilter preFilter() {
        return new PreFilter();
    }
    @Bean
    public PostFilter postFilter() {
        return new PostFilter();
    }
    @Bean
    public ErrorFilter errorFilter() {
        return new ErrorFilter();
    }
    @Bean
    public RouterFilter routeFilter() {
        return new RouterFilter();
    }
}
