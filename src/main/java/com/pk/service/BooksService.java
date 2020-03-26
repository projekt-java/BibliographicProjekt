package com.pk.service;

import com.pk.model.Book;

import java.time.format.TextStyle;
import java.util.Locale;

public class BooksService {

    // creates string from book data to be saved as bibTex
    public static String bookToBibTexString(Book book) {

        String format = "@book";
        String key = "book";
        StringBuilder bibTexString = new StringBuilder(format);

        bibTexString.append("{");
        bibTexString.append(key);
        bibTexString.append(",\n");

        bibTexString.append("author = {");
        bibTexString.append(book.getAuthor());
        bibTexString.append("},\n");

        bibTexString.append("title = {");
        bibTexString.append(book.getTitle());
        bibTexString.append("},\n");

        bibTexString.append("year = {");
        bibTexString.append(book.getPublished_date().getYear());
        bibTexString.append("},\n");

        bibTexString.append("month = ");
        bibTexString.append(book.getPublished_date().getMonth().getDisplayName(TextStyle.SHORT, Locale.ENGLISH).toLowerCase());
        bibTexString.append(",\n");

        bibTexString.append("publisher = {");
        bibTexString.append(book.getPublisher());
        bibTexString.append("},\n");

        bibTexString.append("price = ");
        bibTexString.append(book.getPrice());
        bibTexString.append(",\n");

        bibTexString.append("note = {Genre: ");
        bibTexString.append(book.getGenre());
        bibTexString.append(", desc: ");
        bibTexString.append(book.getDescription());
        bibTexString.append("}}\n");

        return bibTexString.toString();
    }

}
