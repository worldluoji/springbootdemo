package com.springboot.redis.lockdemo.demo.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;

@Configuration
public class RedissonConfig {

    @Value("${spring.redis.password}")
    private String redisPass;

    @Value("${spring.redis.host}")
    private String redisHost;

    @Value("${spring.redis.port}")
    private String redisPort;


    @Bean
    public RedissonClient getRedissonClient() throws UnsupportedEncodingException, URISyntaxException {
        
        Config config = new Config();
        // config.useClusterServers()
        //         .setScanInterval(2000) // 集群状态扫描间隔时间，单位是毫秒
        //         .addNodeAddress("redis://" + redisHost + ":" + redisPort)
        //         .setPassword(redisPass)
        //         .setConnectTimeout(10000)
        //         .setTimeout(2000);
        config.useSingleServer()
            .setAddress("redis://" + redisHost + ":" + redisPort)
            .setConnectTimeout(10000)
            .setTimeout(2000);
        RedissonClient redissonClient = Redisson.create(config);
      
        return redissonClient;
    }

}
