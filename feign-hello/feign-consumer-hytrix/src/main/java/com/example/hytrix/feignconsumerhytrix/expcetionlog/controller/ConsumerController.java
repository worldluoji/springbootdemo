package com.example.hytrix.feignconsumerhytrix.expcetionlog.controller;

import com.example.hytrix.feignconsumerhytrix.expcetionlog.service.ProductService;
import com.feign.feignprovider.model.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@RestController
@RequestMapping(path = "/consumer")
public class ConsumerController {
	@Autowired
	private ProductService productService;
	
	@RequestMapping(value = "list", method = RequestMethod.GET)
	public List<Product> listProduct(){
		List<Product> list = this.productService.listProduct();
		return  list;
	}

	@RequestMapping(value = "get1", method = RequestMethod.GET)
	public Product getProductById(@RequestParam("id") Long id) {
		return productService.getProductById(id);
	}

	@RequestMapping(value = "get2", method = RequestMethod.GET)
	public Product getProductByIdAndName(@RequestParam("id") Long id, @RequestParam("name") String name) {
		return productService.getProductByIdAndName(id, name);
	}

	//采用httpclient解决了feign自动转post的问题
	@RequestMapping(value = "get3", method = RequestMethod.GET)
	public Product getProjectByPoJo(Product product) {
		return productService.getProjectByPoJo(product);
	}

	@RequestMapping(value = "add", method = RequestMethod.POST)
	public Product addProduct(@RequestBody Product product) {
		if (product == null) {
			log.error("invalid input product");
			return new Product();
		}
		return productService.addProduct(product);
	}
}
