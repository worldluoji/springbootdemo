package com.example.eslearn.repository;

import java.util.List;

import com.example.eslearn.models.GeneralTestCase;

import org.springframework.data.repository.Repository;

public interface TestCaseRepository extends Repository<GeneralTestCase, String> {
    List<GeneralTestCase> findBySystem(String system);
    GeneralTestCase save(GeneralTestCase generalTestCase);
}
