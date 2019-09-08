package com.springboot.redis.lockdemo.demo.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.misc.URIBuilder;

import java.io.UnsupportedEncodingException;

public class RedissonConfig {

    private static final String redisPass = "199114";

    private volatile static RedissonClient redissonClient;

    public static RedissonClient getRedissonClient() throws UnsupportedEncodingException {
        if (redissonClient == null) {
            synchronized (RedissonConfig.class) {
                if (redissonClient == null) {
                    Config config = new Config();
                    config.useClusterServers()
                            .setScanInterval(2000) // 集群状态扫描间隔时间，单位是毫秒
                            .addNodeAddress("rediss://127.0.0.1:6379")
                            .setPassword(redisPass);
                    redissonClient = Redisson.create(config);
                }
            }
        }
        return redissonClient;
    }

}
