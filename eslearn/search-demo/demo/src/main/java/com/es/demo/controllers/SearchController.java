package com.es.demo.controllers;

import java.util.List;

import com.es.demo.models.Bank;
import com.es.demo.models.SearchRequest;
import com.es.demo.services.SearchService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SearchController {
    @Autowired
    private SearchService searchService;

    @PostMapping(path="search")
    public List<Bank> search(@RequestBody @Validated SearchRequest request) {
        return searchService.getBankListByKeyWord(request.getKeyword());
    }
}
