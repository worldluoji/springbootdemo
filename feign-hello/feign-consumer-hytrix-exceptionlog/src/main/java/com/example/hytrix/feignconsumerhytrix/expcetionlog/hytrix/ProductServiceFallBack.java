package com.example.hytrix.feignconsumerhytrix.expcetionlog.hytrix;

import com.example.hytrix.feignconsumerhytrix.expcetionlog.service.ProductService;
import com.feign.feignprovider.model.Product;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Component
@Slf4j
public class ProductServiceFallBack implements FallbackFactory<ProductService> {
    @Override
    public ProductService create(Throwable throwable) {
        return new ProductService() {
            @Override
            public List<Product> listProduct() {
                log.warn("fallback exception:{}",throwable);
                return Arrays.asList(Product.builder().name("托底数据").price(0.0).updateTime(new Date()).build());
            }

            @Override
            public Product getProductById(Long id) {
                log.warn("fallback exception:{}, id:{}", throwable, id);
                return Product.builder().name("托底数据").price(0.0).updateTime(new Date()).build();
            }

            @Override
            public Product getProductByIdAndName(Long id, String name) {
                log.warn("fallback exception:{}, id:{}, name:{}", throwable, id, name);
                return Product.builder().name("托底数据").price(0.0).updateTime(new Date()).build();
            }

            @Override
            public Product getProjectByPoJo(Product product) {
                log.warn("fallback exception:{}",throwable);
                return Product.builder().name("托底数据").price(0.0).updateTime(new Date()).build();
            }

            @Override
            public Product addProduct(Product product) {
                log.warn("fallback exception:{}",throwable);
                return Product.builder().name("托底数据").price(0.0).updateTime(new Date()).build();
            }
        };
    }

}
