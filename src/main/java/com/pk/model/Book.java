package com.pk.model;

import java.time.LocalDate;

public class Book {
    private String author;
    private String title;
    private String genre;
    private LocalDate published_date;
    private double price;
    private String description;

    public Book(String author, String title, String genre, LocalDate published_date, double price, String description) {
        this.author = author;
        this.title = title;
        this.genre = genre;
        this.published_date = published_date;
        this.price = price;
        this.description = description;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public LocalDate getPublished_date() {
        return published_date;
    }

    public void setPublished_date(LocalDate published_date) {
        this.published_date = published_date;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
