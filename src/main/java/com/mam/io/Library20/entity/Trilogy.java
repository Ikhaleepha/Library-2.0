package com.mam.io.Library20.entity;

public class Trilogy extends Book{

    private String seriesId;

    public Trilogy(String isbn, String title, Genre genre, String seriesId) {
        super(isbn, title, genre);
        this.seriesId = seriesId;
    }

    public String getSeriesId() {
        return seriesId;
    }

    public void setSeriesId(String seriesId) {
        this.seriesId = seriesId;
    }

    public void getSeries(){

    }

    public Book getPrequel(){
        return null;
    }

    public Book getSequel(){
        return null;
    }
}
