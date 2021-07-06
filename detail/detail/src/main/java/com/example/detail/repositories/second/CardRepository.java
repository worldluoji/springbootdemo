package com.example.detail.repositories.second;

import com.example.detail.models.Card;

import org.springframework.data.repository.CrudRepository;

public interface CardRepository extends CrudRepository<Card, Integer> {
    
}
