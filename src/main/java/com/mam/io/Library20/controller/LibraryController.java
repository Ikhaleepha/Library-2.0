package com.mam.io.Library20.controller;

import com.mam.io.Library20.entity.Book;
import com.mam.io.Library20.entity.Borrow;
import com.mam.io.Library20.entity.ReturnBook;
import com.mam.io.Library20.entity.Student;
import com.mam.io.Library20.error.BookNotFoundException;
import com.mam.io.Library20.error.StudentNotFoundException;
import com.mam.io.Library20.repository.BookRepository;
import com.mam.io.Library20.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
        Optional<Book> optionalBook = libraryService.getBook(isbn);

        if(!optionalBook.isPresent())
            throw new BookNotFoundException();

        return optionalBook.get();
    }

    @PostMapping(path="/borrow")
    public String borrowBook(@RequestBody Borrow borrow){
        return libraryService.borrowBook(borrow);
    }

    @GetMapping(path="/students/{id}")
    public Student getStudent(@PathVariable String id){
        Optional<Student> optionalStudent = libraryService.getStudent(id);

        if(!optionalStudent.isPresent())
            throw new StudentNotFoundException();

        return optionalStudent.get();
    }

    @PostMapping(path="/returnBook")
    public String borrowBook(@RequestBody ReturnBook returnBook){
        return libraryService.returnBook(returnBook);
    }
}
