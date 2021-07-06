package com.example.detail.configs;


import javax.sql.DataSource;

import java.util.HashMap;

import javax.persistence.EntityManagerFactory;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef="entityManagerFactoryPrimary",
        transactionManagerRef="transactionManagerPrimary",
        basePackages= { "com.example.detail.repositories.primary" }) //设置Repository所在位置
public class StudentDataSourceConfig {

    @Bean(name="studentDataSource")
    @ConfigurationProperties("student.datasource")
    public DataSource StudentDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    public EntityManagerFactoryBuilder entityManagerFactoryBuilder() {
        return new EntityManagerFactoryBuilder(new HibernateJpaVendorAdapter(), new HashMap<>(), null);
    }

    @Primary
    @Bean(name = "entityManagerFactoryPrimary")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryPrimary(EntityManagerFactoryBuilder builder, @Qualifier("studentDataSource") DataSource studentDataSource) {
        return builder.dataSource(studentDataSource)
        .packages("com.example.detail.models")
        .persistenceUnit("student")
        .build();
    }

    @Primary
    @Bean(name = "transactionManagerPrimary")
    public PlatformTransactionManager transactionManagerPrimary(@Qualifier("entityManagerFactoryPrimary") EntityManagerFactory entityManagerFactoryPrimary) {
        return new JpaTransactionManager(entityManagerFactoryPrimary);
    }

}