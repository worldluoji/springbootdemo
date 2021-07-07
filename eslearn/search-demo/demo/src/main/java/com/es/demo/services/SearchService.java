package com.es.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import com.es.demo.models.Bank;
import com.es.demo.repositories.*;

@Service
public class SearchService {

    @Autowired
    private SearchRepository searchRepository;

    public List<Bank> getBankListByKeyWord(String keyword) {
        return searchRepository.findByKeyword(keyword);
    }
    
}
