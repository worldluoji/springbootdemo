package com.feign.feignprovider.facade;

import com.feign.feignprovider.model.Product;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequestMapping("/product")
public interface ProductFacade {
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public List<Product> listProduct();

    @RequestMapping(value = "get1", method = RequestMethod.GET)
    public Product getProductById(@RequestParam("id") Long id);

    @RequestMapping(value = "get2", method = RequestMethod.GET)
    public Product getProductByIdAndName(@RequestParam("id")  Long id, @RequestParam("name") String name);

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public Product addProduct(@RequestBody Product product);
}
