package com.pk.reader;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BooksReaderFactoryTest {

    @Test
    void getBooksWriter_givenWrongArgument_shouldThrowIllegalArgumentException() {
        int argument = -10000;
        assertThrows(IllegalArgumentException.class, () -> BooksReaderFactory.getBooksReader(argument));
    }

    @Test
    void getBooksWriter_givenXml_shouldReturnXmlReader() {
        // given
        int argument = BooksReaderFactory.XML;

        // when
        BooksReader booksReader = BooksReaderFactory.getBooksReader(argument);

        // then
        assertTrue(booksReader instanceof XmlReader);
    }
}