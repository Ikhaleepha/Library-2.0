package com.mam.io.Library20.repository;

import com.mam.io.Library20.entity.Borrow;
import com.mam.io.Library20.entity.ReturnBook;
import com.mam.io.Library20.entity.Student;
import com.mam.io.Library20.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.InitDestroyAnnotationBeanPostProcessor;
import org.springframework.stereotype.Component;

import java.util.Dictionary;
import java.util.HashMap;
import java.util.Map;

@Component
public class LibraryRepository {

    private final Map<String, Integer> initialCatalogueState;

    BookRepository bookRepository;

    StudentRepository studentRepository;

    private Map<Integer, Borrow> borrowedList = new HashMap<>();



    private Map<String, Integer> bookCatalogue = new HashMap<>(
            Map.of(
            "01231",3,
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
        initialCatalogueState = bookCatalogue;
    }

    public Map<String, Integer> getBookCatalogue() {
        return bookCatalogue;
    }

    public Map<Integer, Borrow> getBorrowedList() {
        return Map.copyOf(this.borrowedList);
    }

    public void borrowBook(Borrow borrow) {
        borrowedList.put(borrow.hashCode(), borrow);
        int numberOfCopies = bookCatalogue.get(borrow.getBookIsbn());
        setNumberOfBookCopies(borrow.getBookIsbn(),numberOfCopies - 1 );
    }

    public void returnBook(ReturnBook returnBook) {
        borrowedList.remove(returnBook.hashCode());
        int numberOfCopies = bookCatalogue.get(returnBook.getBookIsbn());
        setNumberOfBookCopies(returnBook.getBookIsbn(),numberOfCopies + 1 );
    }

    public void setNumberOfBookCopies(String bookIsbn, int numberOfCopies){
        bookCatalogue.put(bookIsbn, numberOfCopies);
    }

    public int getNumberOfBookCopies(String bookIsbn){
        return bookCatalogue.get(bookIsbn);
    }

    public void reInitializeLibrary(){
        this.bookCatalogue = this.initialCatalogueState;
        this.borrowedList.clear();
    }
}
