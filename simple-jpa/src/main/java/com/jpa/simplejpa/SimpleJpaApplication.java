package com.jpa.simplejpa;

import com.jpa.simplejpa.models.Coffee;
import com.jpa.simplejpa.models.CoffeeOrder;
import com.jpa.simplejpa.repository.CoffeeOrderRepository;
import com.jpa.simplejpa.repository.CoffeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.Collections;

@Slf4j
@SpringBootApplication
public class SimpleJpaApplication implements ApplicationRunner {

    @Autowired
    private CoffeeRepository coffeeRepository;

    @Autowired
    private CoffeeOrderRepository coffeeOrderRepository;

    public static void main(String[] args) {
        SpringApplication.run(SimpleJpaApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        initOrder();
    }

    private void initOrder() {
        Coffee  espresso = Coffee.builder().name("espresso")
                            .price(Money.of(CurrencyUnit.of("CNY"), 20.0))
                            .build();
        coffeeRepository.save(espresso);
        log.info("Coffee is {}", espresso.getName());

        Coffee  latte = Coffee.builder().name("latte")
                .price(Money.of(CurrencyUnit.of("CNY"), 30.0))
                .build();
        coffeeRepository.save(latte);
        log.info("Coffee is {}", latte.getName());

        CoffeeOrder order = CoffeeOrder.builder().customer("luoji")
                                .items(Collections.singletonList(espresso))
                                .state(0)
                                .build();
        coffeeOrderRepository.save(order);
        log.info("order is {}", order);

         order = CoffeeOrder.builder().customer("luoji")
                .items(Arrays.asList(espresso, latte))
                .state(0)
                .build();
        coffeeOrderRepository.save(order);
        log.info("order is {}", order);

    }
}
