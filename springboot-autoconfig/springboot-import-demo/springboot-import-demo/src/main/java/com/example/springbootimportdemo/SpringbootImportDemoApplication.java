package com.example.springbootimportdemo;

import com.example.springbootimportdemo.configuration.properties.SuperDream;
import com.example.springbootimportdemo.importannotation.Charmander;
import com.example.springbootimportdemo.importannotation.Pikachu;
import com.example.springbootimportdemo.importannotation.ZeniGame;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringbootImportDemoApplication implements ApplicationRunner {

	@Autowired
	private Pikachu pikachu;

	@Autowired
	private Charmander charmander;

	@Autowired
	private ZeniGame zeniGame;

	@Autowired
	private SuperDream superDream;

	public SpringbootImportDemoApplication() {
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringbootImportDemoApplication.class, args);
	}

	public void testAnnotationImport() {
		if (pikachu != null) {
			pikachu.say();
		}
	}

	public void testSelectorImport() {
		if (charmander != null) {
			charmander.say();
		}
	}

	public void testImportBeanDefinitionRegistrar() {
		if (zeniGame != null) {
			zeniGame.say();
		}
	}

	public void testConfigurationProperties() {
		if (superDream != null) {
			superDream.say();
		}
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		testAnnotationImport();
		testSelectorImport();
		testImportBeanDefinitionRegistrar();
		testConfigurationProperties();
	}
}
