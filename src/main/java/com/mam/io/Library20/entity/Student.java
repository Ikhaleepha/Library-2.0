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

/*    public boolean borrowBook(Book book){
        return borrowedBooks.add(book);
    }

    public boolean returnBook(Book book){
        return borrowedBooks.remove(book);
    }

    public boolean hasBorrowedBook(Book book){
        return borrowedBooks.contains(book);
    }


    public Set<Book> getBorrowBooks() {
        return borrowedBooks;
    }

    public boolean canBorrowBook() {
        return borrowedBooks.size() < 2;
    }*/

}
