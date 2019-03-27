package com.onlinecoffe.springonlinecoffee.repository;

import com.onlinecoffe.springonlinecoffee.model.Coffee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoffeeRepository extends JpaRepository<Coffee, Long> {

}
