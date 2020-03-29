package com.pk.service;

import com.pk.model.Book;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

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
}