package com.pk.writer;

import com.pk.model.Book;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Month;

public class BibTexWriter implements BooksWriter {

    @Override
    public void write(Book[] books, String path) {
        //name of the file should have .bib extension
        String format = "@book";
        try(
                FileWriter file = new FileWriter(path);
                BufferedWriter writer = new BufferedWriter(file)
        ) {
            for(Book book: books) {
                writer.write(format + "{book,\n");
                writer.write("author = {" + book.getAuthor() + "},\n");
                writer.write("title = {" + book.getTitle() + "},\n");
                writer.write("year = {" + book.getPublished_date().getYear() + "},\n");
                writer.write("month = " + monthAbbreviations(book.getPublished_date().getMonth()) + ",\n");
                writer.write("publisher = {" + book.getPublisher() + "},\n");
                writer.write("price = " + book.getPrice() + ",\n");
                writer.write("note = {Genre: " + book.getGenre() + ", desc: " + book.getDescription() + "}\n");
                writer.write("}\n");
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        //System.out.println("Successfully created BibTex file!");
    }

    // bibtex format requires 3 letter abbreviation for month
    private String monthAbbreviations(Month month) {
        String abbreviation = null;
        switch(month) {
            case JANUARY:
                abbreviation = "jan";
                break;
            case FEBRUARY:
                abbreviation = "feb";
                break;
            case MARCH:
                abbreviation = "mar";
                break;
            case APRIL:
                abbreviation = "apr";
                break;
            case MAY:
                abbreviation = "may";
                break;
            case JUNE:
                abbreviation = "jun";
                break;
            case JULY:
                abbreviation = "jul";
                break;
            case AUGUST:
                abbreviation = "aug";
                break;
            case SEPTEMBER:
                abbreviation = "sep";
                break;
            case OCTOBER:
                abbreviation = "oct";
                break;
            case NOVEMBER:
                abbreviation = "nov";
                break;
            case DECEMBER:
                abbreviation = "dec";
                break;
        }

        return abbreviation;
    }

}
