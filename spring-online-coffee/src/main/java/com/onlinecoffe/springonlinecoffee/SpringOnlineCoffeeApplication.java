package com.onlinecoffe.springonlinecoffee;

import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import com.onlinecoffe.springonlinecoffee.controller.Interceptor.PerformanceInterceptor;
import com.onlinecoffe.springonlinecoffee.repository.CoffeeRepository;
import com.onlinecoffe.springonlinecoffee.service.CoffeeService;
import com.onlinecoffe.springonlinecoffee.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Slf4j
@EnableTransactionManagement
@EnableJpaRepositories
@SpringBootApplication
@EnableCaching(proxyTargetClass = true)
public class SpringOnlineCoffeeApplication implements WebMvcConfigurer, ApplicationRunner {

    @Autowired
    private CoffeeRepository coffeeRepository;

    @Autowired
    private CoffeeService coffeeService;

    @Autowired
    private OrderService orderService;

    public static void main(String[] args) {
        SpringApplication.run(SpringOnlineCoffeeApplication.class, args);
    }

    @Bean
    public Hibernate5Module hibernate5Module() {
        return new Hibernate5Module();
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("Coffee Count {}", coffeeService.findAllCoffee().size());
        coffeeService.findAllCoffee().forEach(c -> log.info("coffee {}", c));
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new PerformanceInterceptor())
                .addPathPatterns("/coffee/**").addPathPatterns("/order/**");
    }
}
