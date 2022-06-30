package com.mam.io.Library20.entity;

import java.util.HashSet;
import java.util.Set;

public class Student implements Identifiable{
    private String id;
    private String name;

    private Set<Book> borrowedBooks = new HashSet<>();

    public Student(String id, String name){
        this.id = id;
        this.name = name;
    }

    public String getId(){
        return this.id;
    }

    public String getName(){
        return this.name;
    }

    public Set<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    public boolean borrowBook(Book book){
        return borrowedBooks.add(book);
    }

    public boolean returnBook(String bookIsbn){
        Book book = null;

        for(Book b: borrowedBooks){
            if(b.getId().equalsIgnoreCase(bookIsbn)){
                book = b;
            }
        }

        return borrowedBooks.remove(book);
    }

    public boolean hasBorrowedBook(String bookIsbn){
        for(Book b: borrowedBooks){
            if(b.getId() == bookIsbn)
                return true;
        }

        return false;
    }

}
