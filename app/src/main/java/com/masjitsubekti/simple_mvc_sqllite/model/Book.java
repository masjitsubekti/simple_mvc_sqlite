package com.masjitsubekti.simple_mvc_sqllite.model;

public class Book {
    private long bookId;
    private String bookName;
    private  String bookAuthor;

    public Book(){

    }

    // setter
    public void setBookId(long bookId){
        this.bookId = bookId;
    }

    public void setBookName(String bookName){
        this.bookName = bookName;
    }

    public void setBookAuthor(String bookAuthor){
        this.bookAuthor = bookAuthor;
    }

    // getter
    public long getBookId(){
        return bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public String toString(){
        return bookName + " by " + bookAuthor;
    }
}
