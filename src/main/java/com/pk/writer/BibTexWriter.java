package com.pk.writer;

import com.pk.model.Book;
import com.pk.service.BooksService;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class BibTexWriter implements BooksWriter {

    @Override
    public void write(Book[] books, String path) {
        //name of the file should have .bib extension
        try(
                FileWriter file = new FileWriter(path);
                BufferedWriter writer = new BufferedWriter(file)
        ) {
            for(Book book: books) {
                writer.write(BooksService.bookToBibTexString(book));
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

}
