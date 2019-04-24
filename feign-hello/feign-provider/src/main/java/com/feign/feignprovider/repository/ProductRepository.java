package com.feign.feignprovider.repository;

import com.feign.feignprovider.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Product findByIdAndName(Long id, String name);
}
