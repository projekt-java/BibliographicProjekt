package com.pk.reader;

import com.pk.model.Book;
import com.pk.service.BooksService;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.util.List;
import java.util.zip.DataFormatException;

import static java.util.stream.Collectors.toList;

public class XmlReader implements BooksReader {
    @Override
    public List<Book> read(String path) throws Exception {
        SAXReader reader = new SAXReader();
        Document document = reader.read(path);
        Element rootElement = document.getRootElement();

        String rootName = rootElement.getName();
        if (!rootName.equalsIgnoreCase("books")) {
            throw new DataFormatException("The root element should be named 'books', not " + rootName);
        }
        return rootElement.elements("book").stream().map(BooksService::xmlElementToBook).collect(toList());
    }
}
