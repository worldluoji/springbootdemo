package com.example.detail.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.*;

import javax.sql.DataSource;

@Configuration
public class MutiDataSourceConfiguration {

    @Autowired
    @Qualifier("studentDataSource")
    private DataSource studentDataSource;

    @Autowired
    @Qualifier("cardDataSource")
    private DataSource cardDataSource;
    

    @Bean(name = "dataSource")    
    public MyDataSource createDataSource() {        
        MyDataSource myDataSource = new MyDataSource();        
        Map<Object, Object> map = new HashMap<>();
        map.put("core", studentDataSource);        
        map.put("card", cardDataSource);        
        myDataSource.setTargetDataSources(map);        
        myDataSource.setDefaultTargetDataSource(studentDataSource);        
        return myDataSource;    
    }
}
