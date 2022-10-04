package ru.libraryapp.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Book {
    private long book_id;

    @NotEmpty(message = "Title shouldn't be empty")
    @Size(min = 1, max = 100, message = "Title should be between 1 and 100 characters")
    private String title;

    @NotEmpty(message = "Title shouldn't be empty")
    @Size(min = 1, max = 100, message = "Title should be between 1 and 100 characters")
    private String author;

    @NotNull(message = "Year shouldn't be empty")
    private int year;

    public Book() {
    }

    public Book(long id, String title, String author, int year) {
        this.book_id = id;
        this.title = title;
        this.author = author;
        this.year = year;
    }

    public long getBook_id() {
        return book_id;
    }

    public void setBook_id(long book_id) {
        this.book_id = book_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
