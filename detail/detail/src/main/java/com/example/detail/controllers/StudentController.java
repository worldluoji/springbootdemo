package com.example.detail.controllers;

import com.example.detail.models.Student;
import com.example.detail.services.StudentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping(path = "/stu/add")
    public Student addStudent(@RequestBody Student student) throws Exception {
        return studentService.addStudentWithCard(student);
    }
}
