package com.pk.writer;

import com.pk.model.Book;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;
import java.io.File;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class TxtWriterTest {
    //test if file has content
    @Test
    void write_fileEmpty_false() {

        //Given
        Book book = new Book("J.R.R Tolkien", "The Hobbit or There and Back Again", "fantasy", "Allen & Unwin", LocalDate.of(1937, Month.SEPTEMBER, 21), 50, "The quest of home-loving Bilbo Baggins");
        Book[] books = new Book[1];
        books[0] = book;
        TxtWriter writer = new  TxtWriter();

        //When
        String path = "data.txt";
        writer.write(books, path);

        //Then
        File file = new File(path);
        assertNotEquals(0, file.length());

    }

    //test if file has correct content
    @Test
    void write_fileHasCorrectContent_true() {
        //Given
        Book book = new Book("J.R.R Tolkien", "The Hobbit or There and Back Again", "fantasy", "Allen & Unwin", LocalDate.of(1937, Month.SEPTEMBER, 21), 50, "The quest of home-loving Bilbo Baggins");
        Book book2 = new Book("J.K Rowling", "Harry Potter and the Philosopher's Stone", "fantasy", "Scholastic Corporation", LocalDate.of(1997, Month.JUNE, 26), 40, "Book about wizards");
        Book[] books = new Book[2];
        books[0] = book;
        books[1] = book2;
        TxtWriter writer = new  TxtWriter();

        //When
        String path = "data.txt";
        writer.write(books, path);

        //Then
        String fileShouldContain = books[0].toString()+"\n"+books[1].toString()+"\n";
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
        assertEquals(fileShouldContain, fileContent.toString());
    }



}