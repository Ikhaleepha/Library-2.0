package com.mam.io.Library20.error;

public class BookNotFoundException extends EntityNotFoundException{
    public BookNotFoundException(){
        super("Book Does Not Exist!");
    }
}
