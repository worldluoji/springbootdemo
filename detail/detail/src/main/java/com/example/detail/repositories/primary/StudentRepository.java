package com.example.detail.repositories.primary;

import com.example.detail.models.Student;

import org.springframework.data.repository.CrudRepository;

public interface StudentRepository extends CrudRepository<Student, Integer> {
    
}
