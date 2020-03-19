package com.pk.writer;

import com.pk.model.Book;

/**
 * Writes books to files.
 */
public interface BooksWriter {
    /**
     * Writes given books to file.
     * @param books Books that will be written to file.
     * @param path Path of final file.
     */
    void write(Book[] books, String path);
}
