package com.example.eslearn.services;

import java.util.List;

import com.example.eslearn.models.GeneralTestCase;
import com.example.eslearn.repository.TestCaseRepository;
import com.google.common.collect.Lists;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestcaseService {

    @Autowired
    private TestCaseRepository testCaseRepository;

    public List<GeneralTestCase> getBySystem(String system) {
        if (system == null || system.isEmpty()) {
            return Lists.newArrayList();
        }
        return this.testCaseRepository.findBySystem(system);
    }

    public GeneralTestCase save(GeneralTestCase generalTestCase) {
        if (generalTestCase == null) {
            throw new IllegalArgumentException("[TestcaseService]input param null");
        }
        return this.testCaseRepository.save(generalTestCase);
    }
}


