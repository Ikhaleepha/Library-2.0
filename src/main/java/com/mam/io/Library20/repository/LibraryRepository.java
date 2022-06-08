package com.mam.io.Library20.repository;

import com.mam.io.Library20.entity.Borrow;
import com.mam.io.Library20.entity.Student;
import com.mam.io.Library20.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Dictionary;
import java.util.HashMap;
import java.util.Map;

@Component
public class LibraryRepository {


    BookRepository bookRepository;

    StudentRepository studentRepository;

    private Map<Integer, Borrow> borrowedList = new HashMap<>();

    private Map<String, Integer> bookCatalogue = new HashMap<>(
            Map.of(
            "01231",5,
            "01232",4,
            "01233",3,
            "01234",2,
            "01235",7,
            "01236",3,
            "01237",7,
            "01238",4,
            "01239",2,
            "01240",4
            )
    );


    public LibraryRepository(){

    }

    public Map<Integer, Borrow> getBorrowedList() {
        return Map.copyOf(this.borrowedList);
    }

    public void borrowBook(Borrow borrow) {
        borrowedList.put(borrow.hashCode(), borrow);
    }
}
