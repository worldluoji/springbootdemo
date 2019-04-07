package com.onlinecoffe.springonlinecoffee.controller;

import com.onlinecoffe.springonlinecoffee.controller.request.NewOrderRequest;
import com.onlinecoffe.springonlinecoffee.model.Coffee;
import com.onlinecoffe.springonlinecoffee.model.Order;
import com.onlinecoffe.springonlinecoffee.service.CoffeeService;
import com.onlinecoffe.springonlinecoffee.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/order")
@Slf4j
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private CoffeeService coffeeService;

    @PostMapping(path = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Order createOrder(@RequestBody @Valid NewOrderRequest newOrder) {
        Coffee[] coffees = coffeeService.getCoffeesByName(newOrder.getItems()).toArray(new Coffee[]{});
        return orderService.createOrder(newOrder.getCustomer(), coffees);
    }

    @GetMapping(path = "/{id}")
    public Order getOrder(@PathVariable("id") Long id) {
        Order order = orderService.get(id);
        log.info("Get order {}", order);
        return order;
    }
}
