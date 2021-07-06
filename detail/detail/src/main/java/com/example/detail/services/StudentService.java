package com.example.detail.services;

import com.example.detail.models.Card;
import com.example.detail.models.Student;
import com.example.detail.repositories.primary.StudentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CardService cardService;


    public Student addStudent(Student student) {
        if (!StringUtils.hasLength(student.getName())) {
            throw new IllegalArgumentException("student name can not be null");
        }
        return studentRepository.save(student);
    }

    @Transactional(rollbackFor = Exception.class)
    public Student addStudentWithCard(Student student) throws Exception {
        Student s = this.addStudent(student);
        if (s != null && s.getId() != null) {
            Card card = new Card();
            card.setStudentId(s.getId());
            // 构造异常抛出
            // card.setBalance(null);
            card.setBalance(50);
            cardService.createCard(card);
        }
        return s;
    }
    
}
