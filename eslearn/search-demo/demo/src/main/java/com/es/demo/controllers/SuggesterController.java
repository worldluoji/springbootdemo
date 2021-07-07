package com.es.demo.controllers;

import java.util.List;

import com.es.demo.models.SearchRequest;
import com.es.demo.services.SuggesterService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SuggesterController {

    @Autowired
    private SuggesterService suggesterService;

    @PostMapping(path="suggest")
    public List<String> getSuggestedWords(@RequestBody @Validated SearchRequest request) {
        return suggesterService.getSuggestedWords(request.getKeyword());
    }
}
