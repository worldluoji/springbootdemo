package com.example.springbootimportdemo.configuration.properties;

import com.example.springbootimportdemo.importannotation.Pokemon;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "manager")
public class SuperDream extends Pokemon {
}
