package com.mam.io.Library20.entity;

public class Book implements Identifiable{

    private String isbn;
    private String title;

    public Book(String isbn, String title, Genre genre){
        this.isbn = isbn;
        this.title = title;
    }

    public String getId(){
        return this.isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }



}
