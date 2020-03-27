package com.pk.service;

import com.pk.model.Book;

import java.time.format.TextStyle;
import java.util.Locale;

public class BooksService {

    /**
     * <p>Returns string made of all book elements for saving to BibTex format.</p>
     * @param book Book of which string will be returned
     * @return String in BibTex format
     */
    public static String bookToBibTexString(Book book) {
        String format = "@book";
        String key = "book";
        StringBuilder bibTexString = new StringBuilder(format);

        return bibTexString.
                append("{").
                append(key).
                append(",\n").
                append("author = {").
                append(book.getAuthor()).
                append("},\n").
                append("title = {").
                append(book.getTitle()).
                append("},\n").
                append("year = {").
                append(book.getPublished_date().getYear()).
                append("},\n").
                append("month = ").
                append(book.getPublished_date().getMonth().getDisplayName(TextStyle.SHORT, Locale.ENGLISH).toLowerCase()).
                append(",\n").
                append("publisher = {").
                append(book.getPublisher()).
                append("},\n").
                append("price = ").
                append(book.getPrice()).
                append(",\n").
                append("note = {Genre: ").
                append(book.getGenre()).
                append(", desc: ").
                append(book.getDescription()).
                append("}}\n").toString();
    }
}
