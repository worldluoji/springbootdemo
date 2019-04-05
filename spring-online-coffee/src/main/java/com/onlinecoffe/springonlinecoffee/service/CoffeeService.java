package com.onlinecoffe.springonlinecoffee.service;

import com.onlinecoffe.springonlinecoffee.model.Coffee;
import com.onlinecoffe.springonlinecoffee.repository.CoffeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.joda.money.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.exact;

@Slf4j
@Service
@CacheConfig(cacheNames = "coffee")
public class CoffeeService {

    @Autowired
    private CoffeeRepository coffeeRepository;

    public Coffee saveCoffee(String coffeeName, Money price) {
        return coffeeRepository.save(Coffee.builder().name(coffeeName).price(price).build());
    }

    public Coffee getCoffee(Long coffeeId) {
        return coffeeRepository.findById(coffeeId).get();
    }

    public Coffee getCoffee(String coffeeName) {
        return coffeeRepository.findByName(coffeeName);
    }

    public List<Coffee> getCoffeesByName(List<String> coffeeNames) {
        return coffeeRepository.findByNameInOrderById(coffeeNames);
    }

    public Optional<Coffee> findOneCoffee(String name) {
        ExampleMatcher matcher = ExampleMatcher.matching().withMatcher("name", exact().ignoreCase());
        Optional<Coffee> coffee = coffeeRepository.findOne(
                Example.of(Coffee.builder().name(name).build(), matcher));
        log.info("Coffee found {}", coffee);
        return coffee;
    }

    @Cacheable
    public List<Coffee> findAllCoffee() {
        log.info("find all coffee...");
        return coffeeRepository.findAll();
    }

    @CacheEvict
    public void reloadCoffee() {
        log.info("Reload Coffee Success");
    }
}
