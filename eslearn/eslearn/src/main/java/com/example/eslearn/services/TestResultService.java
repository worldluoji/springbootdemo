package com.example.eslearn.services;

import com.example.eslearn.models.TestResult;
import com.example.eslearn.repository.TestResultRepository;

import org.elasticsearch.ElasticsearchCorruptionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TestResultService {
    
    @Autowired
    private TestResultRepository repository;

    public TestResult save(TestResult result) throws ElasticsearchCorruptionException {
        if (result == null) {
            throw new IllegalArgumentException("[TestResultService]save : input param null");
        }
        log.info("begin to save to Elasticsearch:", result.toString());
        TestResult res = repository.save(result);
        if (res == null) {
            log.error("[TestResultService]save : fail to send data to es");
            throw new ElasticsearchCorruptionException("[TestResultService]save : fail to send data to es");
        }
        return res;
    }
}
