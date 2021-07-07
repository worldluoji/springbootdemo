package com.es.demo.repositories;

import java.util.List;

import com.es.demo.models.Bank;

import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface SearchRepository extends ElasticsearchRepository<Bank, String> {
    @Query("{\"multi_match\": {\"query\": \"?0\",\"fields\": [\"firstname\", \"lastname\", \"address\"]}}")
    List<Bank> findByKeyword(String keyword);
}