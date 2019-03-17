package com.datasource.singledatasource;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Slf4j
@SpringBootApplication
public class SingleDatasourceApplication implements CommandLineRunner {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public static void main(String[] args) {
        SpringApplication.run(SingleDatasourceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        showConnections();
        showData();
    }

    private void showConnections() throws SQLException {
        log.info(dataSource.toString());
        Connection conn = dataSource.getConnection();
        conn.close();
        log.info(conn.toString());
    }

    private void showData() throws SQLException {
        jdbcTemplate.queryForList("SELECT * FROM foo")
                .forEach(row -> log.info(row.toString()));
    }

}
