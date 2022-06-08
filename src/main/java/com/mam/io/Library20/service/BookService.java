package com.mam.io.Library20.service;

import com.mam.io.Library20.entity.Book;
import com.mam.io.Library20.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class BookService {
    private final BookRepository bookRepository;


    public BookService(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }

    public List<Book> getAllBooks(){
        return bookRepository.getAllEntities();
    }

    public Optional<Book> getBook(String isbn){
        return bookRepository.exist(isbn);
    }


}
