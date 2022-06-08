package com.mam.io.Library20.service;

import com.mam.io.Library20.entity.Book;
import com.mam.io.Library20.entity.Borrow;
import com.mam.io.Library20.entity.LibraryEntity;
import com.mam.io.Library20.entity.Student;
import com.mam.io.Library20.error.BookNotFoundException;
import com.mam.io.Library20.error.StudentNotFoundException;
import com.mam.io.Library20.repository.LibraryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
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



    public String borrowBook(Borrow borrow){
        if(libraryRepository.getBorrowedList().containsKey(borrow.hashCode())){
            throw new RuntimeException("Student has already borrowed the book");
        }

        Collection<Borrow> borrows = libraryRepository.getBorrowedList().values();

        int count = 0;

        for (Borrow b: borrows) {
            if(b.getStudentId().equalsIgnoreCase
                    (borrow.getStudentId()))
                count = count + 1;
        }

        if(count >= 2)
            throw new RuntimeException("Student has reached a limit of two books");

        libraryRepository.borrowBook(borrow);

        return "borrowed successfully";

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
