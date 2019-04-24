package com.feign.feignprovider.facade;

import com.feign.feignprovider.model.Product;
import com.feign.feignprovider.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
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

    @Override
    public Product getProductById(@RequestParam("id") Long id) {
        return productService.getById(id);
    }

    @Override
    public Product getProductByIdAndName(@RequestParam("id") Long id,@RequestParam("name") String name) {
        return productService.getByIdAndName(id, name);
    }

    @Override
    public Product addProduct(@RequestBody Product product) {
        return productService.addProduct(product);
    }
}
