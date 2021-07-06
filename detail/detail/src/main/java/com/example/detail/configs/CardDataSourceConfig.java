package com.example.detail.configs;


import javax.sql.DataSource;


import javax.persistence.EntityManagerFactory;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef="entityManagerFactorySecond",
        transactionManagerRef="transactionManagerSecond",
        basePackages= { "com.example.detail.repositories.second" }) //设置Repository所在位置
public class CardDataSourceConfig {

    @Bean(name="cardDataSource")
    @ConfigurationProperties("card.datasource")
    public DataSource cardDataSource() {
        return DataSourceBuilder.create().build();
    }


    @Bean(name = "entityManagerFactorySecond")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryPrimary(EntityManagerFactoryBuilder builder, @Qualifier("cardDataSource") DataSource cardDataSource) {
        return builder.dataSource(cardDataSource)
        .packages("com.example.detail.models")
        .persistenceUnit("card")
        .build();
    }

    @Bean(name = "transactionManagerSecond")
    public PlatformTransactionManager transactionManagerPrimary(@Qualifier("entityManagerFactorySecond") EntityManagerFactory entityManagerFactorySecond) {
        return new JpaTransactionManager(entityManagerFactorySecond);
    }

}