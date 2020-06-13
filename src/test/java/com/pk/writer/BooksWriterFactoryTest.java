package com.pk.writer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BooksWriterFactoryTest {

    @Test
    void getBooksWriter_wrongArgument_shouldThrowIllegalArgumentException() {
        int argument = 100;

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            BooksWriterFactory.getBooksWriter(argument);
        });
    }
}