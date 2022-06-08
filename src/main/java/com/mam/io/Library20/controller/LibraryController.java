package com.mam.io.Library20.controller;

import com.mam.io.Library20.entity.Book;
import com.mam.io.Library20.repository.BookRepository;
import com.mam.io.Library20.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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


}
