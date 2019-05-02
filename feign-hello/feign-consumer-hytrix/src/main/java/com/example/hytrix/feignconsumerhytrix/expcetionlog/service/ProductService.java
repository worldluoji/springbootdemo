package com.example.hytrix.feignconsumerhytrix.expcetionlog.service;

import com.example.hytrix.feignconsumerhytrix.expcetionlog.feignconfig.FeignClientConfig;
import com.example.hytrix.feignconsumerhytrix.expcetionlog.hytrix.ProductServiceFallBack;
import com.feign.feignprovider.model.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name="e-book-product", fallback = ProductServiceFallBack.class, configuration = FeignClientConfig.class)
public interface ProductService {
    @RequestMapping(value = "product/list", method = RequestMethod.GET)
    public List<Product> listProduct();

    @RequestMapping(value = "product/get1", method = RequestMethod.GET)
    public Product getProductById(@RequestParam("id") Long id);

    @RequestMapping(value = "product/get2", method = RequestMethod.GET)
    public Product getProductByIdAndName(@RequestParam("id") Long id, @RequestParam("name") String name);

    @RequestMapping(value = "product/get3", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Product getProjectByPoJo(Product product);

    @RequestMapping(value = "product/add", method = RequestMethod.POST)
    public Product addProduct(@RequestBody Product product);
}
