package com.pk.writer;

import com.pk.model.Book;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Implements BooksWriter
 * writes Books to txt file
 */
public class TxtWriter implements BooksWriter {
    /**
     * Writes content of Book objects to txt file
     * one object per line
     *
     * @param books Books that will be written to file.
     * @param path Path of final file.
     */

    @Override
    public void write(Book[] books, String path){

        try(
                FileWriter file = new FileWriter(path);
                BufferedWriter writer = new BufferedWriter(file)
        ) {
                for ( Book book: books){
                    writer.write(book.toString()+"\n");
                }

        } catch (IOException exception) {
            exception.printStackTrace();
        }

    }
}
