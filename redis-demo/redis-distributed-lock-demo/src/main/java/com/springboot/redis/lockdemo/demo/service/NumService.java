package com.springboot.redis.lockdemo.demo.service;

import com.springboot.redis.lockdemo.demo.config.RedissonConfig;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.concurrent.TimeUnit;

@Service
public class NumService {
    private long num = 0;

    private RLock lock;
    public NumService() throws UnsupportedEncodingException {
        lock = RedissonConfig.getRedissonClient().getLock("ADD_ONE_LOCK");
    }

    public long addOne() {
        num++;
        return num;
    }

    public long atomicAddOne() throws InterruptedException {
        lock.tryLock(10,1, TimeUnit.SECONDS);
        num++;
        lock.unlock();
        return num;
    }

    public long getNum() {
        return this.num;
    }

    public void clear() {
        num = 0;
    }

}
