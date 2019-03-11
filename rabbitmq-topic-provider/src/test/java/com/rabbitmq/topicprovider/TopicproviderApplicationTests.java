package com.rabbitmq.topicprovider;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TopicproviderApplicationTests {

    @Autowired
    private UserSend userSend;

    @Test
    public void contextLoads() {
    }

    @Test
    public void send() throws InterruptedException {
        System.out.println("Before send...");
        this.userSend.send();
        System.out.println("After send...");
    }

}
