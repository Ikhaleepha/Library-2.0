package com.mam.io.Library20.service;

import com.mam.io.Library20.entity.Book;
import com.mam.io.Library20.entity.Student;
import com.mam.io.Library20.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }

    public Optional<Student> getStudent(String studentId){
        return studentRepository.exist(studentId);
    }

    public void borrowBook(String studentId, Book book){
        getStudent(studentId).get().borrowBook(book);
    }
/*
    public boolean exists(String studentid){
        return studentRepository.exists(studentid);
    }
    public void borrowBook(String studentId, Book book){
        getStudent(studentId).borrowBook(book);
    }

    public boolean canBorrowBook(String studentId){
        return getStudent(studentId).canBorrowBook();
    }

    public boolean successfullyBorrowed(Book book){
        return false;
    }*/
}
