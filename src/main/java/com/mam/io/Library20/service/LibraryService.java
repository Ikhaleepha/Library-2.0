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

    public Optional<Student> getStudent(String studentId){
        return studentService.getStudent(studentId);
    }

    public List<Book> getAllBooks(){
        return bookService.getAllBooks();
    }

    public Optional<Book> getBook(String isbn){
        return bookService.getBook(isbn);
    }

    public boolean isBookAvailable(String bookIsbn){
        return libraryRepository.getBookCatalogue()
                .get(bookIsbn) > 0;
    }

    public boolean bookExists(String bookIsbn){
        return (
                getBook(bookIsbn).isPresent() &&
                libraryRepository.getBookCatalogue().containsKey(bookIsbn)
                );

    }

    public int getNumberOfBooksBorrowed(Borrow borrow){
        Collection<Borrow> borrows = libraryRepository.getBorrowedList().values();

        int count = 0;

        for (Borrow b: borrows) {
            if(b.getStudentId().equalsIgnoreCase
                    (borrow.getStudentId()))
                count = count + 1;
        }

        return count;
    }

    public String borrowBook(Borrow borrow){

        if(!studentService.getStudent(borrow.getStudentId()).isPresent()) {
            throw new StudentNotFoundException();
        }

        if(!bookExists(borrow.getBookIsbn())){
            throw new RuntimeException("Book Does Not Exist in the library");
        }

        if(!isBookAvailable(borrow.getBookIsbn())){
            throw new RuntimeException("Book is not available");
        }

        if(hasBorrowedBook(borrow)){
            throw new RuntimeException("Student has already borrowed the book");
        }

        /*if(libraryRepository.getBorrowedList().containsKey(borrow.hashCode())){
            throw new RuntimeException("Student has already borrowed the book");
        }
*/

        if(getNumberOfBooksBorrowed(borrow) >= 2)
            throw new RuntimeException("Student has reached a limit of two books");


        libraryRepository.borrowBook(borrow);
        studentService.borrowBook(borrow.getStudentId(),bookService.getBook(borrow.getBookIsbn()).get());

        return "borrowed successfully";

    }

    public void emptyLibrary() {
        bookService.emptyLibrary();
    }

    public void populateLibrary() {
        bookService.populateLibrary();
    }

    public void setNumberOfCopies(String bookIsbn, int numberOfCopies){
        libraryRepository.setNumberOfBookCopies(bookIsbn,numberOfCopies);
    }

    public int getNumberOfCopies(String bookIsbn){
        return libraryRepository.getNumberOfBookCopies(bookIsbn);
    }

    public boolean hasBorrowedBook(Borrow borrow){
        return libraryRepository.getBorrowedList().containsKey(borrow.hashCode());
    }

    public void reInitializeLibrary() {
        libraryRepository.reInitializeLibrary();
        studentService.reInitializeLibrary();
    }
}
