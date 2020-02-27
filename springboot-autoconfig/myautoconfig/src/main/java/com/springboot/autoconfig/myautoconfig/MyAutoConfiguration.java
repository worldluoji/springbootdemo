package com.springboot.autoconfig.myautoconfig;

import com.springboot.autoconfig.greeting.GreetingApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
* 扩展注解作用
 * ConditionalOnBean
 * 容器中存在指定 Bean，则生效。
 *
 * ConditionalOnMissingBean
 * 容器中不存在指定 Bean，则生效。
 *
 * ConditionalOnClass
 * 系统中有指定的类，则生效。
 *
 * ConditionalOnMissingClass
 * 系统中没有指定的类，则生效。
 *
 * ConditionalOnProperty
 * 系统中指定的属性是否有指定的值。
 *
 * ConditionalOnWebApplication
 * 当前是web环境，则生效。
* */
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
