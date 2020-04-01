package com.pk.reader;

import com.pk.model.Book;

import java.util.List;

public interface BooksReader {
    List<Book> read(String path) throws Exception;
}