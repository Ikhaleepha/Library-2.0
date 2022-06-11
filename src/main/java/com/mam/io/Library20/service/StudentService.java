package com.mam.io.Library20.service;

import com.mam.io.Library20.entity.Book;
import com.mam.io.Library20.entity.Student;
import com.mam.io.Library20.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public void reInitializeLibrary() {
        for(int i = 0; i < getAllStudents().size(); i++){
            getAllStudents().get(i)
                    .getBorrowBooks().clear();
        }
    }

    private List<Student> getAllStudents() {
        return studentRepository.getAllEntities();
    }
}
