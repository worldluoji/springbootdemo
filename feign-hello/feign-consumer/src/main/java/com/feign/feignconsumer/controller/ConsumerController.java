package com.feign.feignconsumer.controller;

import java.util.List;

import com.feign.feignconsumer.service.ProductService;
import com.feign.feignprovider.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping(path = "/consumer")
public class ConsumerController {
	@Autowired
	private ProductService productService;
	
	@RequestMapping(value = "list",method = RequestMethod.GET)
	public List<Product> listProduct(){
		List<Product> list = this.productService.listProduct();
		return  list;
	}
}
