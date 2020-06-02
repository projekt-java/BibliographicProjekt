package com.pk.model;


import java.time.LocalDate;
import java.util.Objects;

public class Book {
    private String author;
    private String title;
    private String genre;
    private String publisher;
    private LocalDate publishedDate;
    private double price;
    private String description;

    public Book(String author, String title, String genre, String publisher, LocalDate publishedDate, double price, String description) {
        this.author = author;
        this.title = title;
        this.genre = genre;
        this.publisher = publisher;
        this.publishedDate = publishedDate;
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

    public String getPublisher() { return publisher; }

    public void setPublisher(String publisher) { this.publisher = publisher; }

    public LocalDate getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(LocalDate publishedDate) {
        this.publishedDate = publishedDate;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Double.compare(book.price, price) == 0 &&
                Objects.equals(author, book.author) &&
                Objects.equals(title, book.title) &&
                Objects.equals(genre, book.genre) &&
                Objects.equals(publisher, book.publisher) &&
                Objects.equals(publishedDate, book.publishedDate) &&
                Objects.equals(description, book.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(author, title, genre, publisher, publishedDate, price, description);
    }

    @Override
    public String toString() {
        return "Book{" +
                "author='" + author + '\'' +
                ", title='" + title + '\'' +
                ", genre='" + genre + '\'' +
                ", publisher='" + publisher + '\'' +
                ", published_date=" + publishedDate +
                ", price=" + price +
                ", description='" + description + '\'' +
                '}';
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private String author;
        private String title;
        private String genre;
        private String publisher;
        private LocalDate publishedDate;
        private double price;
        private String description;

        public Builder author(String author) {
            this.author = author;
            return this;
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder genre(String genre) {
            this.genre = genre;
            return this;
        }

        public Builder publisher(String publisher) {
            this.publisher = publisher;
            return this;
        }

        public Builder publishedDate(LocalDate publishedDate) {
            this.publishedDate = publishedDate;
            return this;
        }

        public Builder price(double price) {
            this.price = price;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Book build() {
            return new Book(author, title, genre, publisher, publishedDate, price, description);
        }
    }
}
