package com.reactor.simplereactordemo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.concurrent.CountDownLatch;

@Slf4j
@SpringBootApplication
public class SimpleReactorDemoApplication implements ApplicationRunner {

    private final CountDownLatch countDownLatch = new CountDownLatch(1);

    public static void main(String[] args) {
        SpringApplication.run(SimpleReactorDemoApplication.class, args);
    }


    /**
    * publishOn里实际是执行了Flux.publishOn(Scheduler scheduler, int prefetch)，
     * prefetch是Queues.SMALL_BUFFER_SIZE，这里的SMALL_BUFFER_SIZE是256
    * */
    @Override
    public void run(ApplicationArguments args) throws Exception {
        Flux.range(1, 6)
                .doOnRequest(n -> log.info("Request number {}", n))
                .doOnComplete(() -> log.info("Publisher complete 1"))
                .publishOn(Schedulers.elastic())
                .map(i -> {
                    log.info("Publish {},{}", Thread.currentThread(), i );
                    return 90 / (i - 3);
                }).doOnComplete(() -> log.info("Publisher complete 2"))
                .subscribeOn(Schedulers.single())
                //.onErrorReturn(-1)
                .onErrorResume(e -> {
					log.error("Exception {}", e.toString());
					return Mono.just(-1);
				})
                .subscribe(i -> log.info("Subscribe {},{}", Thread.currentThread(), i ),
                        e -> log.error("error {}", e.toString()),
                        () -> {
                            log.info("Subscribe complete");
                            countDownLatch.countDown();
                        }
                        //s -> s.request(3)   //只取3個request
                );
        countDownLatch.await();
    }
}
