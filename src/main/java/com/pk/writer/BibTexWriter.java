package com.pk.writer;

import com.pk.model.Book;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class BibTexWriter implements BooksWriter {
    @Override
    public void write(Book[] books, String path) {
        String format = "@book";
        try(
                FileWriter file = new FileWriter(path);
                BufferedWriter writer = new BufferedWriter(file)
        ) {
            for(Book book: books) {
                writer.write(format + "{book,");
                writer.newLine();
                writer.write("author = {" + book.getAuthor() + "},");
                writer.newLine();
                writer.write("title = {" + book.getTitle() + "},");
                writer.newLine();
                writer.write("year = " + book.getPublished_date() + ",");
                writer.newLine();
                writer.write("genre = {" + book.getGenre() + "},");
                writer.newLine();
                writer.write("price = " + book.getPrice() + ",");
                writer.newLine();
                writer.write("description = {" + book.getDescription() + "}");
                writer.newLine();
                writer.write("}");
                writer.newLine();
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        System.out.println("Successfully created BibTex file!");
    }
}
