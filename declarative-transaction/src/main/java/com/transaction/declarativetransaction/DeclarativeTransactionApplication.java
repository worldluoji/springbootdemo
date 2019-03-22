package com.transaction.declarativetransaction;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

@Slf4j
@SpringBootApplication
public class DeclarativeTransactionApplication implements CommandLineRunner {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private FooService fooService;

    public static void main(String[] args) {
        SpringApplication.run(DeclarativeTransactionApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        fooService.insertRecored();
        log.info("AAa {}", jdbcTemplate.queryForObject("SELECT COUNT(*) FROM FOO WHERE BAR='AAa'", Long.class));

        try {
            fooService.insertThenRollback();
        } catch (RollBackException e) {
            log.info("1BBb {}", jdbcTemplate.queryForObject("SELECT COUNT(*) FROM FOO WHERE BAR='BBb'", Long.class));
        }

        try {
            fooService.invokeInsertThenRollback();
        } catch (RollBackException e) {
            log.info("2BBb {}", jdbcTemplate.queryForObject("SELECT COUNT(*) FROM FOO WHERE BAR='BBb'", Long.class));
        }
    }

}
