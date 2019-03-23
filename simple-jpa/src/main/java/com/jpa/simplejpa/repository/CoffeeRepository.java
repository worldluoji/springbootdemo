package com.jpa.simplejpa.repository;

import com.jpa.simplejpa.models.Coffee;
import org.springframework.data.repository.CrudRepository;

public interface CoffeeRepository extends CrudRepository<Coffee, Long> {

}
