package com.pk.reader;

import com.pk.model.Book;
import com.pk.service.BooksService;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.util.List;
import java.util.zip.DataFormatException;

import static java.util.stream.Collectors.toList;

/**
 * Converts xml data to {@link Book} objects.
 */
public class XmlReader implements BooksReader {
    @Override
    public List<Book> read(String path) throws Exception {
        // looking for root element
        SAXReader reader = new SAXReader();
        Document document = reader.read(path);
        Element rootElement = document.getRootElement();

        // checking if root has proper name
        String rootName = rootElement.getName();
        if (!rootName.equalsIgnoreCase("books")) {
            throw new DataFormatException("The root element should be named 'books', not " + rootName);
        }

        // mapping root's elements to books
        return rootElement.elements("book").stream().map(BooksService::xmlElementToBook).collect(toList());
    }
}
