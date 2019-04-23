package com.feign.feignconsumer.service;

import com.feign.feignprovider.facade.ProductFacade;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name="e-book-product")
public interface ProductService extends ProductFacade {
	
}
