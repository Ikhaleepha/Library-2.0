package com.mam.io.Library20.entity;

import java.util.Objects;

public class Borrow {

    public Borrow(String studentId, String bookIsbn) {
        this.studentId = studentId;
        this.bookIsbn = bookIsbn;
    }

    private final String studentId;
    private final String bookIsbn;

    public String getStudentId() {
        return studentId;
    }

    public String getBookIsbn() {
        return bookIsbn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Borrow)) return false;
        Borrow borrow = (Borrow) o;
        return studentId.equals(borrow.studentId) && bookIsbn.equals(borrow.bookIsbn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId, bookIsbn);
    }
}
