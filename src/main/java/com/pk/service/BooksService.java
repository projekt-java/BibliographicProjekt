package com.pk.service;

import com.pk.adapter.ElementAdapter;
import com.pk.model.Book;
import org.dom4j.Element;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.time.format.TextStyle;
import java.util.Locale;
import java.util.Set;

import static java.lang.Double.*;
import static java.util.Optional.ofNullable;

public class BooksService {

    /**
     * <p>Returns string made of all book elements for saving to BibTex format.</p>
     * @param book Book of which string will be returned.
     * @param keys Unique set of BibTex keys.
     * @return String in BibTex format.
     */
    public static String bookToBibTexString(Book book, Set<String> keys) {

        String format = "@book";
        String key = getBibTexKey(book, keys);

        StringBuilder bibTexString = new StringBuilder(format);
        return bibTexString
                .append("{")
                .append(key)
                .append(",\n")
                .append("author = {")
                .append(book.getAuthor())
                .append("},\n")
                .append("title = {")
                .append(book.getTitle())
                .append("},\n")
                .append("year = {")
                .append(book.getPublishedDate().getYear())
                .append("},\n")
                .append("month = ")
                .append(book.getPublishedDate().getMonth().getDisplayName(TextStyle.SHORT, Locale.ENGLISH).toLowerCase())
                .append(",\n")
                .append("publisher = {")
                .append(book.getPublisher())
                .append("},\n")
                .append("price = ")
                .append(book.getPrice())
                .append(",\n")
                .append("note = {Genre: ")
                .append(book.getGenre())
                .append(", desc: ")
                .append(book.getDescription())
                .append("}}\n")
                .toString();
    }

    /**
     * <p>Returns key string for BibTex format for given book and adds key to set of keys.</p>
     * @param book Book of which key will be returned.
     * @param keys Unique set of BibTex keys.
     * @return BibTex key.
     */
    private static String getBibTexKey(Book book, Set<String> keys) {

        // key = author surname + published year + '_num'
        String key;
        String authorFullName = book.getAuthor().toLowerCase();
        if(authorFullName.contains(" ")) {
            String[] authorSurname = authorFullName.split(" ");
            key = authorSurname[authorSurname.length - 1] + book.getPublishedDate().getYear();
        } else {
            key = authorFullName + book.getPublishedDate().getYear();
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

    /**
     * Converts xml {@link Element} to {@link Book}. If some data is missing, the adequate field will be null.
     * @param el Xml {@link Element} that will be converted to {@link Book}.
     * @return {@link Book} converted from xml {@link Element}.
     */
    public static Book xmlElementToBook(Element el) {
        String stringDate = ofNullable(el.element("published_date")).orElse(new ElementAdapter(null)).getText();

        LocalDate publishedDate = null;
        try {
            publishedDate = LocalDate.parse(stringDate);
        } catch (DateTimeParseException ignored) {}

        double price = 10.0;
        try {
            price = parseDouble(ofNullable(el.element("price")).orElse(new ElementAdapter("10.0")).getText());
        } catch (NumberFormatException ignored) {}

        // Every lines checks if value exists. ofNullable method checks if expected element exists, if not, orElse
        // method returns custom element.
        return Book.builder()
                .author(ofNullable(el.element("author")).orElse(new ElementAdapter(null)).getText())
                .title(ofNullable(el.element("title")).orElse(new ElementAdapter(null)).getText())
                .genre(ofNullable(el.element("genre")).orElse(new ElementAdapter(null)).getText())
                .publisher(ofNullable(el.element("publisher")).orElse(new ElementAdapter(null)).getText())
                .publishedDate(publishedDate)
                .price(price > 0 ? price : 10)
                .description(ofNullable(el.element("description")).orElse(new ElementAdapter(null)).getText())
                .build();
    }
}
