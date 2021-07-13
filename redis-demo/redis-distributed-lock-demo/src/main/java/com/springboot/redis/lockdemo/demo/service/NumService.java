package com.springboot.redis.lockdemo.demo.service;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

@Service
public class NumService {

    @Autowired
    private RedissonClient redissionClient;

    private long num = 0;

    private RLock lock;

    @PostConstruct
    public void init() {
        lock = redissionClient.getLock("ADD_ONE_LOCK");
    }

    public long addOne() {
        num++;
        return num;
    }

    public long atomicAddOne() throws InterruptedException {
        /* 
        ** tryLock(long waitTime, long leaseTime, TimeUnit unit)
        ** waitTime 获取锁的等待时间
        ** leaseTime 锁超时释放时间
        ** TimeUnit指定前面两个参数的单位
        */
        if (lock.tryLock(5, 1, TimeUnit.SECONDS)) {
            num++;
            lock.unlock();
        }
        return num;
    }

    public long getNum() {
        return this.num;
    }

    public void clear() {
        num = 0;
    }

}
