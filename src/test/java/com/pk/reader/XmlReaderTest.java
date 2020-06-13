package com.pk.reader;

import com.pk.model.Book;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.zip.DataFormatException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class XmlReaderTest {

    @Test
    void read_properData_shouldReturnListWithBooks() throws Exception {
        // given
        String path = "books_test.xml";
        String xml = "<?xml version='1.0' encoding='UTF-8'?>\n" +
                "<books>\n" +
                "    <book>\n" +
                "        <author>Rickard Androli</author>\n" +
                "        <title>Like Minds (Murderous Intent)</title>\n" +
                "        <genre>Crime|Mystery|Thriller</genre>\n" +
                "        <publisher>HarperCollins</publisher>\n" +
                "        <published_date>1996-11-16</published_date>\n" +
                "        <price>59.25</price>\n" +
                "        <description>desc</description>\n" +
                "    </book>\n" +
                "    <book>\n" +
                "        <author>Alia Ciccottio</author>\n" +
                "        <title>Snakes on a Plane</title>\n" +
                "        <genre>Action|Comedy|Horror|Thriller</genre>\n" +
                "        <publisher>Penguin Random House</publisher>\n" +
                "        <published_date>1994-12-21</published_date>\n" +
                "        <price>59.62</price>\n" +
                "        <description>desc</description>\n" +
                "    </book>\n" +
                "</books>";
        Files.write(Paths.get(path), xml.getBytes());

        // when
        XmlReader xmlReader = new XmlReader();
        List<Book> actual = xmlReader.read(path);

        // then
        List<Book> expected = Stream.of(
                new Book("Rickard Androli","Like Minds (Murderous Intent)", "Crime|Mystery|Thriller",
                        "HarperCollins", LocalDate.parse("1996-11-16"), 59.25, "desc"),
                new Book("Alia Ciccottio", "Snakes on a Plane", "Action|Comedy|Horror|Thriller",
                        "Penguin Random House", LocalDate.parse("1994-12-21"), 59.62, "desc")
                ).collect(Collectors.toList());
        assertEquals(expected, actual);

        Files.delete(Paths.get(path));
    }

    @Test
    void read_someFieldsAreMissing_fieldsShouldBeNull() throws Exception {
        // given
        String path = "books_test.xml";
        String xml = "<?xml version='1.0' encoding='UTF-8'?>\n" +
                "<books>\n" +
                "    <book>\n" +
                "    </book>\n" +
                "    <book>\n" +
                "        <author>Alia Ciccottio</author>\n" +
                "        <title>Snakes on a Plane</title>\n" +
                "        <genre>Action|Comedy|Horror|Thriller</genre>\n" +
                "        <publisher>Penguin Random House</publisher>\n" +
                "        <published_date>1994-12-21</published_date>\n" +
                "        <price>59.62</price>\n" +
                "        <description>desc</description>\n" +
                "    </book>\n" +
                "</books>";
        Files.write(Paths.get(path), xml.getBytes());

        // when
        XmlReader xmlReader = new XmlReader();
        List<Book> actual = xmlReader.read(path);

        // then
        List<Book> expected = Stream.of(
                new Book(null, null, null, null, null, 10, null),
                new Book("Alia Ciccottio", "Snakes on a Plane", "Action|Comedy|Horror|Thriller",
                        "Penguin Random House", LocalDate.parse("1994-12-21"), 59.62, "desc")
        ).collect(Collectors.toList());
        assertEquals(expected, actual);

        Files.delete(Paths.get(path));
    }

    @Test
    void read_wrongRootName_shouldThrowDataFormatException() throws Exception {
        String path = "books_test.xml";
        String xml = "<?xml version='1.0' encoding='UTF-8'?>\n" +
                "<not_books>\n" +
                "    <book>\n" +
                "        <author>Alia Ciccottio</author>\n" +
                "        <title>Snakes on a Plane</title>\n" +
                "        <genre>Action|Comedy|Horror|Thriller</genre>\n" +
                "        <publisher>Penguin Random House</publisher>\n" +
                "        <published_date>1994-12-21</published_date>\n" +
                "        <price>59.62</price>\n" +
                "        <description>desc</description>\n" +
                "    </book>\n" +
                "</not_books>";
        Files.write(Paths.get(path), xml.getBytes());

        XmlReader xmlReader = new XmlReader();
        assertThrows(DataFormatException.class, () -> xmlReader.read(path));

        Files.delete(Paths.get(path));
    }
}