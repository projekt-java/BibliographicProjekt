package com.pk.writer;

public class BooksWriterFactory {
    public static final int BIBTEX = 0;
    public static final int RTF = 1;
    public static final int TXT = 2;

    public static BooksWriter getBooksWriter(int format) {
        switch (format) {
            case BIBTEX:
                return null; // todo return bibtex writer
            case RTF:
                return null; // todo return rtf writer
            case TXT:
                return null; // todo return txt writer
            default:
                throw new IllegalArgumentException();
        }
    }
}
