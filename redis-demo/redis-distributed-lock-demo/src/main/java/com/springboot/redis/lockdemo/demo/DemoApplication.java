package com.springboot.redis.lockdemo.demo;

import com.springboot.redis.lockdemo.demo.service.NumService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.concurrent.CompletableFuture;

@Slf4j
@SpringBootApplication//(scanBasePackages = {"com.springboot.redis.lockdemo.demo"})
public class DemoApplication implements ApplicationRunner {

    @Autowired
    private NumService numService;

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    private void  testDemo1() {
        CompletableFuture<Long> f1 = CompletableFuture.supplyAsync(() -> {
            long result = 0;
            for (int i=0; i < 100; i++) {
                result = numService.addOne();
            }
            return result;
        });

        CompletableFuture<Long> f2 = CompletableFuture.supplyAsync(() -> {
            long result = 0;
            for (int i=0; i < 100; i++) {
                result = numService.addOne();
            }
            return result;
        });

        f1.join();
        f2.join();
        log.info("The result is {}", numService.getNum());
    }

    private void  testDemo2() {
        CompletableFuture<Long> f1 = CompletableFuture.supplyAsync(() -> {
            try {
                long result = 0;
                for (int i=0; i < 100; i++) {
                    result = numService.atomicAddOne();
                }
                return result;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return -1l;
        });

        CompletableFuture<Long> f2 = CompletableFuture.supplyAsync(() -> {
            try {
                long result = 0;
                for (int i=0; i < 100; i++) {
                    result = numService.atomicAddOne();
                }
                return result;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return -1l;
        });

        f1.join();
        f2.join();
        log.info("The result is {}", numService.getNum());
    }



    @Override
    public void run(ApplicationArguments args) throws Exception {
        numService.clear();
        testDemo1();
        numService.clear();
        testDemo2();
    }
}
