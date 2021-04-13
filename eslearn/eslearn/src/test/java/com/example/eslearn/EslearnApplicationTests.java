package com.example.eslearn;

import com.example.eslearn.controller.TestController;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class EslearnApplicationTests {


	@Autowired
	private TestController testController;

	@Test
	void contextLoads() {
		testController.autoTest();
	}

}
