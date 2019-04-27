package com.feign.feignprovider.facade;

import com.feign.feignprovider.model.Product;
import com.feign.feignprovider.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
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
    public Product getProjectByPoJo(Product product) {
        if (product.getId() == null) {
            throw new IllegalArgumentException("Product Id 不能为空");
        }

        if (StringUtils.isEmpty(product.getName())) {
            return productService.getById(product.getId());
        }

        return productService.getByIdAndName(product.getId(), product.getName());
    }

    @Override
    public Product addProduct(@RequestBody Product product) {
        return productService.addProduct(product);
    }
}
