package com.pk.writer;

import com.pk.model.Book;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.time.LocalDate;
import java.time.Month;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class BibTexWriterTest {

    // tests if content of written file is what it should be
    @Test
    void write_contentInFileSameAsExpected_true() {

        // Given
        Book book = new Book("J.R.R Martin", "A Song of Ice and Fire", "fantasy", "Bantam Books", LocalDate.of(1996, Month.JULY, 1), 50, "Basically Game of thrones");
        Book[] books = new Book[1];
        books[0] = book;

        BibTexWriter writer = new BibTexWriter();

        // When
        String path = "data.bib";
        writer.write(books, path);

        // Then
        String fileShouldContain = "@book{book,\n" +
                "author = {J.R.R Martin},\n" +
                "title = {A Song of Ice and Fire},\n" +
                "year = {1996},\n" +
                "month = jul,\n" +
                "publisher = {Bantam Books},\n" +
                "price = 50.0,\n" +
                "note = {Genre: fantasy, desc: Basically Game of thrones}}\n";

        StringBuilder fileContent = new StringBuilder();
        File file = new File(path);
        try(Scanner scanner = new Scanner(file)) {
            while(scanner.hasNextLine()) {
                fileContent.append(scanner.nextLine());
                fileContent.append("\n");
            }
        } catch(Exception e) {
            e.printStackTrace();
        }

        //System.out.println(fileContent);
        assertEquals(fileShouldContain, fileContent.toString());
    }

    // tests if created file has content
    @Test
    void write_fileEmpty_false() {

        // Given
        Book book = new Book("J.K Rowling", "Harry Potter and the Philosopher's Stone", "fantasy", "Scholastic Corporation", LocalDate.of(1997, Month.JUNE, 26), 40, "Book about wizards");
        Book[] books = new Book[1];
        books[0] = book;
        BibTexWriter writer = new BibTexWriter();

        // When
        String path = "data.bib";
        writer.write(books, path);

        // Then
        File file = new File(path);
        assertNotEquals(0, file.length());
    }
}