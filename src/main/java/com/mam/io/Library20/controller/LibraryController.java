package com.mam.io.Library20.controller;

import com.mam.io.Library20.entity.Book;
import com.mam.io.Library20.entity.Borrow;
import com.mam.io.Library20.entity.LibraryEntity;
import com.mam.io.Library20.entity.Student;
import com.mam.io.Library20.error.BookNotFoundException;
import com.mam.io.Library20.repository.BookRepository;
import com.mam.io.Library20.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class LibraryController {

    @Autowired
    private LibraryService libraryService;

    @Autowired
    BookRepository bookRepository;

    public LibraryController(LibraryService libraryService){
        this.libraryService = libraryService;
    }

    @GetMapping(path="/")
    public String welcomePage(){
        return "Welcome to Mam's Library";
    }

    @GetMapping(path="/books")
    public List<Book> getAllBooks(){
        return libraryService.getAllBooks();
    }

    @GetMapping(path = "/books/{isbn}")
    public Book getBook(@PathVariable String isbn){
        return libraryService.getBook(isbn);

        //return "The ISBN is " + isbn;
    }

    @PostMapping(path="/borrow")
    public String borrowBook(@RequestBody Borrow borrow){
        return libraryService.borrowBook(borrow);
    }
/*
    @PutMapping(path = "/books/borrow/{studentId}/{isbn}")
    public void borrowBook(@PathVariable String studentId, @PathVariable String isbn){
        libraryService.borrowBook(studentId, isbn);
    }
    *//*

    @PutMapping(path = "/books/return/{isbn}")
    public String returnBook(@PathVariable String isbn){
        return bookService.returnBook(isbn);
    }*/
}
