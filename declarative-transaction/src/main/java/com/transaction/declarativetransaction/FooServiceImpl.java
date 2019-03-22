package com.transaction.declarativetransaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FooServiceImpl implements FooService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    @Transactional
    public void insertRecored() {
        jdbcTemplate.update("INSERT INTO FOO(BAR) VALUES('AAa') ");
    }

    @Override
    @Transactional(rollbackFor = RollBackException.class)
    public void insertThenRollback() throws RollBackException {
        jdbcTemplate.update("INSERT INTO FOO(BAR) VALUES('BBb') ");
        throw new RollBackException();
    }

    @Override
    public void invokeInsertThenRollback() throws RollBackException {
        insertThenRollback();
    }
}
