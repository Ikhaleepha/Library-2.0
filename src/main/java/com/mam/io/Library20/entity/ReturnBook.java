package com.mam.io.Library20.entity;

import java.util.Objects;

public class ReturnBook {

    private String studentId;
    private String bookIsbn;
    public ReturnBook(String studentId, String bookIsbn) {
        this.studentId = studentId;
        this.bookIsbn = bookIsbn;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getBookIsbn() {
        return bookIsbn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ReturnBook)) return false;
        ReturnBook that = (ReturnBook) o;
        return getStudentId().equals(that.getStudentId()) && getBookIsbn().equals(that.getBookIsbn());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getStudentId(), getBookIsbn());
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public void setBookIsbn(String bookIsbn) {
        this.bookIsbn = bookIsbn;
    }
}
