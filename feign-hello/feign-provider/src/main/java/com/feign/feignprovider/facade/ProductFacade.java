package com.feign.feignprovider.facade;

import com.feign.feignprovider.model.Product;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@RequestMapping("/product")
public interface ProductFacade {
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public List<Product> listProduct();
}
