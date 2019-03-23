package com.jpa.simplejpa.repository;

import com.jpa.simplejpa.models.CoffeeOrder;
import org.springframework.data.repository.CrudRepository;

public interface CoffeeOrderRepository extends CrudRepository<CoffeeOrder, Long> {

}
