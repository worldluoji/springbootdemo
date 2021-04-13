package com.example.eslearn.repository;

import java.util.List;

import com.example.eslearn.models.TestResult;

import org.springframework.data.repository.Repository;

public interface TestResultRepository extends Repository<TestResult, String> {
    List<TestResult> findById(String Id);
    TestResult save(TestResult result);
}