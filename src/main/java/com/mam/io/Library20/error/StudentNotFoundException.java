package com.mam.io.Library20.error;

public class StudentNotFoundException extends EntityNotFoundException{

    public StudentNotFoundException(){
        super("Student Does Not Exist!");
    }
}
