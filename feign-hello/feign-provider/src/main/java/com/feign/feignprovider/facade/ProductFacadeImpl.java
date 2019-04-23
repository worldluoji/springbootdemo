package com.feign.feignprovider.facade;

import com.feign.feignprovider.model.Product;
import com.feign.feignprovider.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductFacadeImpl implements ProductFacade {

    @Autowired
    private ProductService productService;

    @Override
    public List<Product> listProduct() {
        return productService.listProduct();
    }
}
