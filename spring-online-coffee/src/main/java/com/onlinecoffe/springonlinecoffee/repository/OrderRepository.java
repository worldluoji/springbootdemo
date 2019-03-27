package com.onlinecoffe.springonlinecoffee.repository;

import com.onlinecoffe.springonlinecoffee.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
