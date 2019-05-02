package com.example.hytrix.feignconsumerhytrix.expcetionlog.hytrix;

import com.example.hytrix.feignconsumerhytrix.expcetionlog.service.ProductService;
import com.feign.feignprovider.model.Product;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Component
public class ProductServiceFallBack implements ProductService {

    @Override
    public List<Product> listProduct() {
        return Arrays.asList(Product.builder().name("托底数据").price(0.0).updateTime(new Date()).build());
    }

    @Override
    public Product getProductById(Long id) {
        return Product.builder().name("托底数据").price(0.0).updateTime(new Date()).build();
    }

    @Override
    public Product getProductByIdAndName(Long id, String name) {
        return Product.builder().name("托底数据").price(0.0).updateTime(new Date()).build();
    }

    @Override
    public Product getProjectByPoJo(Product product) {
        return Product.builder().name("托底数据").price(0.0).updateTime(new Date()).build();
    }

    @Override
    public Product addProduct(Product product) {
        return Product.builder().name("托底数据").price(0.0).updateTime(new Date()).build();
    }
}
