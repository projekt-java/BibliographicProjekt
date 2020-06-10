package com.pk.service;

import com.pk.adapter.ElementAdapter;
import com.pk.model.Book;
import org.dom4j.Element;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BooksServiceTest {

    @Test
    void bookToBibTexString_stringExpected_true() {

        // Given
        Set<String> keys = new HashSet<>();
        Book book = new Book("J.R.R Martin", "A Song of Ice and Fire", "fantasy", "Bantam Books", LocalDate.of(1996, Month.JULY, 1), 50, "Basically Game of thrones");

        // When
        String bibTexString = BooksService.bookToBibTexString(book, keys);

        // Then
        String expected = "@book{martin1996,\n" +
                "author = {J.R.R Martin},\n" +
                "title = {A Song of Ice and Fire},\n" +
                "year = {1996},\n" +
                "month = jul,\n" +
                "publisher = {Bantam Books},\n" +
                "price = 50.0,\n" +
                "note = {Genre: fantasy, desc: Basically Game of thrones}}\n";

        assertEquals(expected, bibTexString);
    }

    @Test
    void xmlElementToBook_properData_shouldReturnBookWithoutNulls() {
        // given
        Element element = new ElementAdapter() {
            @Override
            public Element element(String s) {
                switch (s) {
                    case "author":
                        return new ElementAdapter("author");
                    case "title":
                        return new ElementAdapter("title");
                    case "genre":
                        return new ElementAdapter("genre");
                    case "publisher":
                        return new ElementAdapter("publisher");
                    case "published_date":
                        return new ElementAdapter("2000-01-01");
                    case "price":
                        return new ElementAdapter("19.99");
                    case "description":
                        return new ElementAdapter("description");
                    default:
                        return null;
                }
            }
        };

        // when
        Book actual = BooksService.xmlElementToBook(element);

        // then
        Book expected = new Book("author", "title", "genre", "publisher",
                LocalDate.parse("2000-01-01"), 19.99, "description");
        assertEquals(expected, actual);
    }

    @Test
    void xmlElementToBook_missingFields_missingFieldsShouldBeNull() {
        // given
        Element element = new ElementAdapter() {
            @Override
            public Element element(String s) {
                return null;
            }
        };

        // when
        Book actual = BooksService.xmlElementToBook(element);

        // then
        Book expected = new Book(null, null, null, null, null, 0,
                null);
        assertEquals(expected, actual);
    }
}