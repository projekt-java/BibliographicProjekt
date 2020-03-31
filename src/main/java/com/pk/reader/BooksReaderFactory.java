package com.pk.reader;

public class BooksReaderFactory {
    public static final int XML = 0;

    public static BooksReader getBooksReader(int format) {
        switch (format) {
            case XML:
                return new XmlReader();
            default:
                throw new IllegalArgumentException();
        }
    }
}
