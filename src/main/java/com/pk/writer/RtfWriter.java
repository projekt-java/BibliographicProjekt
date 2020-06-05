package com.pk.writer;

import com.pk.model.Book;
import com.tutego.jrtf.Rtf;
import com.tutego.jrtf.RtfPara;

import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import static com.tutego.jrtf.RtfDocfmt.defaultTab;
import static com.tutego.jrtf.RtfDocfmt.revisionMarking;
import static com.tutego.jrtf.RtfPara.p;
import static com.tutego.jrtf.RtfUnit.CM;

public class RtfWriter implements BooksWriter {
    @Override
    public void write(Book[] books, String path) {
        try {
            List<RtfPara> paras = new LinkedList<>();
            for (Book book :books) {
                paras.add(p(book.getAuthor()));
                paras.add(p(book.getTitle()));
                paras.add(p(book.getGenre()));
                paras.add(p(book.getPublisher()));
                paras.add(p(book.getPublishedDate()));
                paras.add(p(book.getPrice()));
                paras.add(p(book.getDescription()));
                paras.add(p(""));
            }
            Rtf rtf = Rtf.rtf();
            rtf.documentFormatting(defaultTab(1, CM), revisionMarking()).section(paras);
            rtf.out(new FileWriter(path));
        }
        catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}
