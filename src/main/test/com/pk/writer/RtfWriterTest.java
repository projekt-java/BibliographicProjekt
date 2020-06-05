package com.pk.writer;
import com.pk.model.Book;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.time.LocalDate;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.*;

class RtfWriterTest{
    private final RtfWriter rtfWriter;

    public RtfWriterTest() {
        rtfWriter = new RtfWriter();
    }

//    When this test pass the code inside it will be redundant when writing next tests
//    @Test
//    void ItExists()
//    {
//        rtfWriter = new RtfWriter();
//    }
    @Test
    void IsImplementingAnyInterface()
    {
           //Arrange
           var interfaces = rtfWriter.getClass().getInterfaces();
           //Act
           //Assert
           assertNotEquals(0,interfaces.length);
    }
    @Test
    void IsImplementingBookWriterInterface()
    {
           //Arrange
           var interfaces = rtfWriter.getClass().getInterfaces();
           //Act
            var expected = "interface com.pk.writer.BooksWriter";
           //Assert
           assertEquals(expected,interfaces[0].toString());
    }
    @Test
    void CreateRtfFile()
    {

        //Given
        Book book = new Book("J.R.R Tolkien", "The Hobbit or There and Back Again", "fantasy", "Allen & Unwin", LocalDate.of(1937, Month.SEPTEMBER, 21), 50, "The quest of home-loving Bilbo Baggins");
        Book[] books = new Book[3];
        books[0] = book;
        books[1] = book;
        books[2] = book;



        //When
        String path = "data.rtf";
        rtfWriter.write(books, path);

        //Then
        File file = new File(path);
        assertNotEquals(0, file.length());


    }
}
