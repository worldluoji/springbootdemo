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


    /*
    * 如果不指定rollbackFor参数，则默认发生RuntimeException时回滚
    * */
    @Override
    @Transactional(rollbackFor = RollBackException.class)
    public void insertThenRollback() throws RollBackException {
        jdbcTemplate.update("INSERT INTO FOO(BAR) VALUES('BBb') ");
        throw new RollBackException();
    }

    /*
    * @Transactional注解的原理是使用动态代理，这样调用@Transactional注解无效
    * 正确的方式应该是将对象注入进来后再调用
    * */
    @Override
    public void invokeInsertThenRollback() throws RollBackException {
        insertThenRollback();
    }
}
