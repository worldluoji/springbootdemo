package com.onlinecoffe.springonlinecoffee.service;

import com.onlinecoffe.springonlinecoffee.model.Coffee;
import com.onlinecoffe.springonlinecoffee.model.Order;
import com.onlinecoffe.springonlinecoffee.model.OrderState;
import com.onlinecoffe.springonlinecoffee.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

@Slf4j
@Service
@Transactional
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public Order createOrder(String customer, Coffee...coffee) {
        Order order = Order.builder().customer(customer)
                        .items(Arrays.asList(coffee))
                        .orderState(OrderState.INIT)
                        .build();

        Order saved = orderRepository.save(order);
        log.info("New Order {}", saved);
        return saved;
    }

    public boolean updateOrder(Order order, OrderState orderState) {
        // 只允许状态往大的方向走
        if (orderState.compareTo(order.getOrderState()) <= 0) {
            log.warn("Wrong State order: {}, {}", orderState, order.getOrderState());
            return false;
        }
        order.setOrderState(orderState);
        orderRepository.save(order);
        log.info("Update Order {}", order);
        return true;
    }
}
