package com.pk.service;

import com.pk.model.Book;
import com.pk.writer.BibTexWriter;

import java.time.format.TextStyle;
import java.util.HashSet;
import java.util.Locale;

public class BooksService {

    /**
     * <p>Returns string made of all book elements for saving to BibTex format.</p>
     * @param book Book of which string will be returned.
     * @param keys Unique set of BibTex keys.
     * @return String in BibTex format.
     */
    public static String bookToBibTexString(Book book, HashSet<String> keys) {

        String format = "@book";
        String key = getBibTexKey(book, keys);

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

    /**
     * <p>Returns key string for BibTex format for given book and adds key to set of keys.</p>
     * @param book Book of which key will be returned.
     * @param keys Unique set of BibTex keys.
     * @return BibTex key.
     */
    private static String getBibTexKey(Book book, HashSet<String> keys) {

        // key = author surname + published year + '_num'
        String key;
        String authorFullName = book.getAuthor().toLowerCase();
        if(authorFullName.contains(" ")) {
            String[] authorSurname = authorFullName.split(" ");
            key = authorSurname[authorSurname.length - 1] + book.getPublished_date().getYear();
        } else {
            key = authorFullName + book.getPublished_date().getYear();
        }

        String oldKey = key;
        int num = 1;
        while(keys.contains(key)) {
            key = oldKey;
            key += "_" + num++;
        }

        keys.add(key);

        return key;
    }


}
