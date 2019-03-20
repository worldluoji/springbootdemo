package com.transaction.programmictransaction;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.quartz.QuartzProperties;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

@Slf4j
@SpringBootApplication
public class ProgrammicTransactionApplication implements CommandLineRunner {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private TransactionTemplate transactionTemplate;

    public static void main(String[] args) {
        SpringApplication.run(ProgrammicTransactionApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("Before transaction {}", getCount());
        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus status) {
                jdbcTemplate.update("INSERT INTO FOO(BAR) VALUES(?)", "ppp");
                log.info("COUNT IN TRANSACTION: {}", getCount());
                status.setRollbackOnly(); // 回滚
            }
        });
        log.info("After transaction {}", getCount());
    }

    private long getCount() {
        return (long)jdbcTemplate.queryForList("SELECT COUNT(*) AS CNT FROM FOO").get(0).get("CNT");
    }
}
