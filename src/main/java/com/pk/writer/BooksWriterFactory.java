package com.pk.writer;

import javafx.stage.FileChooser;

/**
 * Returns specific {@link BooksWriter}.
 */
public class BooksWriterFactory {
    public static final int BIBTEX = 0;
    public static final int RTF = 1;
    public static final int TXT = 2;

    public static FileChooser.ExtensionFilter getExtensionsFilter(int format) {
        switch (format) {
            case BIBTEX:
                return new FileChooser.ExtensionFilter("BibTeX", ".bib");
            case RTF:
                return new FileChooser.ExtensionFilter("RTF", ".rtf");
            case TXT:
                return new FileChooser.ExtensionFilter("TXT", ".txt");
            default:
                throw new IllegalArgumentException();
        }
    }

    /**
     * <p>Returns {@link BooksWriter}. You can choose specific writer by giving one of the constants that represents
     * format of final file:</p>
     * <p>{@link #BIBTEX}</p>
     * <p>{@link #RTF}</p>
     * <p>{@link #TXT}</p>
     * <p>If you give wrong argument {@link IllegalArgumentException} will be thrown.</p>
     * @param format Format of final file.
     * @return Specific {@link BooksWriter}.
     */
    public static BooksWriter getBooksWriter(int format) {
        switch (format) {
            case BIBTEX:
                return new BibTexWriter();
            case RTF:
                return new RtfWriter();
            case TXT:
                return new TxtWriter();
            default:
                throw new IllegalArgumentException();
        }
    }
}
