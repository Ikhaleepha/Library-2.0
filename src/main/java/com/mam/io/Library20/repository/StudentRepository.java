package com.mam.io.Library20.repository;

import com.mam.io.Library20.entity.Student;
import com.mam.io.Library20.service.StudentService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StudentRepository extends Repository<Student>{

    public StudentRepository(List<Student> entityList) {
        super(List.of
                (
                        new Student("01", "Obiora Oke"),
                        new Student("02","Muhammad Bashir"),
                        new Student("03", "Damilola John"),
                        new Student("04", "Ben Stark"),
                        new Student("05", "Sean King")
                )
        );
    }
}

