package com.mam.io.Library20.service;

import com.mam.io.Library20.entity.Book;
import com.mam.io.Library20.error.BookNotFoundException;
import com.mam.io.Library20.repository.LibraryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class LibraryService {

    private final LibraryRepository libraryRepository;
    private final BookService bookService;
    private final StudentService studentService;

    public LibraryService(LibraryRepository libraryRepository,BookService bookService, StudentService studentService){
        this.libraryRepository = libraryRepository;
        this.bookService = bookService;
        this.studentService = studentService;
    }

    public List<Book> getAllBooks(){
        return bookService.getAllBooks();
    }

    public Book getBook(String isbn){

        Optional<Book> book = bookService.getBook(isbn);

        if(!book.isPresent())
            throw new BookNotFoundException();

        return book.get();
    }

    /*
    public void emptyLibrary(){
        bookService.emptyLibrary();
    }

    public void populateLibrary(){
        bookService.populateLibrary();
    }

    public void borrowBook(String studentId, String bookIsbn){
        //if book is available and student do not have a copy of the book and can borrow book
        //add the
        //update the number of book copies in the library

    }*/
}
