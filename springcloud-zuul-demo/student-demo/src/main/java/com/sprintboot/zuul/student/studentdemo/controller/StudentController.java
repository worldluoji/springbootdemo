package com.sprintboot.zuul.student.studentdemo.controller;

import com.sprintboot.zuul.student.studentdemo.model.Student;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class StudentController {

    @RequestMapping(value = "echoStudentName/{name}")
    public String echoStudentName(@PathVariable(name="name") String name) {
        return "hello  <strong style=\"color: red;\">" + name + " </strong> Response on : " + new Date();
    }

    @RequestMapping(value="/getStudentDetails/{name}")
    public Student getStudentDetails(@PathVariable(name="name") String name) {
        return new Student(name, "Chengdu", "MSA201");
    }
}
