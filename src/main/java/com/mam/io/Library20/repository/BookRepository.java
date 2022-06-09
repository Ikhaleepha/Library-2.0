package com.mam.io.Library20.repository;


import com.mam.io.Library20.entity.Book;
import com.mam.io.Library20.entity.Genre;
import com.mam.io.Library20.entity.Standalone;
import com.mam.io.Library20.entity.Trilogy;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BookRepository extends Repository<Book>{

    public BookRepository() {

        super(new ArrayList<>(
                List.of(
                        new Standalone("01231", "Java How To Program", Genre.Programming),
                        new Standalone("01232", "A Millionaire Vampire", Genre.Fantasy),
                        new Trilogy("01233", "Fifth Season", Genre.Fantasy, "001"),
                        new Trilogy("01234", "Obelisk Gate", Genre.Fantasy, "001"),
                        new Trilogy("01235", "Stone Sky", Genre.Fantasy, "001"),
                        new Standalone("01236", "Byzantine And Persian Wars", Genre.History),
                        new Standalone("01237", "Make it Stick", Genre.Development),
                        new Trilogy("01238", "Chain of Gold", Genre.Fiction, "002"),
                        new Trilogy("01239", "Chain of Iron", Genre.Fiction, "002"),
                        new Trilogy("01240", "Chain of Thorns", Genre.Fiction, "002"))
        ));
    }

}
