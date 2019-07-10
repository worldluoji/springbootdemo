package com.springboot.autoconfig.myautoconfig;

import com.springboot.autoconfig.greeting.GreetingApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnClass(GreetingApplication.class)
public class MyAutoConfiguration {
    @Bean
    @ConditionalOnMissingBean(GreetingApplication.class)
    @ConditionalOnProperty(name="greeting.enable", havingValue = "true", matchIfMissing = true)
    public GreetingApplication getGreetingApplication() {
        return new GreetingApplication();
    }
}
