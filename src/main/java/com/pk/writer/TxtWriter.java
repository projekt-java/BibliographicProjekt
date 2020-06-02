package com.pk.writer;

import com.pk.model.Book;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;


public class TxtWriter implements BooksWriter {
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
