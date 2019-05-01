package com.feign.feignconsumer.service;

import com.feign.feignconsumer.feignconfig.FeignClientConfig;
import com.feign.feignprovider.facade.ProductFacade;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name="e-book-product", configuration = FeignClientConfig.class)
public interface ProductService extends ProductFacade {
	
}
