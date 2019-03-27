package com.onlinecoffe.springonlinecoffee;

import com.onlinecoffe.springonlinecoffee.model.Coffee;
import com.onlinecoffe.springonlinecoffee.model.Order;
import com.onlinecoffe.springonlinecoffee.model.OrderState;
import com.onlinecoffe.springonlinecoffee.repository.CoffeeRepository;
import com.onlinecoffe.springonlinecoffee.service.CoffeeService;
import com.onlinecoffe.springonlinecoffee.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Optional;

@Slf4j
@SpringBootApplication
public class SpringOnlineCoffeeApplication implements ApplicationRunner {

    @Autowired
    private CoffeeRepository coffeeRepository;

    @Autowired
    private CoffeeService coffeeService;

    @Autowired
    private OrderService orderService;

    public static void main(String[] args) {
        SpringApplication.run(SpringOnlineCoffeeApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("All Coffee {}", coffeeRepository.findAll());
        Optional<Coffee> latte = coffeeService.findOneCoffee("LATTE");
        if (latte.isPresent()) {
            Order order = orderService.createOrder("luoji", latte.get());
            log.info("Update INIT to PAID: {}", orderService.updateOrder(order, OrderState.PAID));
            log.info("Update PAID to INIT: {}", orderService.updateOrder(order, OrderState.INIT));
        }
    }
}
