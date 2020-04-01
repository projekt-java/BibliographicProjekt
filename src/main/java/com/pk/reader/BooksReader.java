package com.pk.reader;

import com.pk.model.Book;

import java.util.List;

/**
 * Reads books from files.
 */
public interface BooksReader {
    /**
     * Reads books from specific file.
     * @param path The path to the file from which the book will be read.
     * @return List of read books.
     * @throws Exception Exception could be thrown at many scenario e.g. when format of data is incorrect or file
     * doesn't exists.
     */
    List<Book> read(String path) throws Exception;
}