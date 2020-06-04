package com.pk.writer;

import com.pk.model.Book;
import com.tutego.jrtf.Rtf;
import java.io.FileWriter;
import java.io.IOException;

import static com.tutego.jrtf.RtfDocfmt.*;
import static com.tutego.jrtf.RtfInfo.*;
import static com.tutego.jrtf.RtfPara.*;
import static com.tutego.jrtf.RtfUnit.*;

public class RtfWriter implements BooksWriter {
    @Override
    public void write(Book[] books, String path) {
        try
        {
            var temp = Rtf.rtf();
            for (Book book :books) {
                        temp.info(author(book.getAuthor()), title(book.getTitle()))
                        .documentFormatting(defaultTab(1, CM), revisionMarking()).section(
                                p(book.getTitle()),
                                p(book.getGenre()),
                                p(book.getPublisher()),
                                p(book.getPublishedDate()),
                                p(book.getPrice()),
                                p(book.getDescription()));
            }
            temp.out(new FileWriter(path));

        }
        catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}
