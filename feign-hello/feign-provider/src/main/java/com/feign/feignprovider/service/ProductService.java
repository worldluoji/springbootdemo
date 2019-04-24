package com.feign.feignprovider.service;

import com.feign.feignprovider.model.Product;
import com.feign.feignprovider.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> listProduct() {
        log.info("Find all products:");
        return productRepository.findAll();
    }

    public Product getById(Long id) {
        return productRepository.getOne(id);
    }

    public Product getByIdAndName(Long id, String name) {
        return productRepository.findByIdAndName(id, name);
    }

    public Product addProduct(Product product) {
        if (product == null) {
            return new Product();
        }
        return productRepository.save(product);
    }

}
