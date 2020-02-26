package com.example.springbootimportdemo.importannotation;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
* @Import 注解支持导入普通 java 类，并将其声明成一个bean。主要用于将多个分散的 java config 配置类融合成一个更大的 config 类。
* */
@Import({Pikachu.class, ImportSelectorConfig.class, ImportBeanDefinitionRegistrarConfig.class})
@Configuration
public class ImportClassConfig {
}
