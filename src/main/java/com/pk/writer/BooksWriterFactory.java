package com.pk.writer;

/**
 * Returns specific {@link BooksWriter}.
 */
public class BooksWriterFactory {
    public static final int BIBTEX = 0;
    public static final int RTF = 1;
    public static final int TXT = 2;

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
                return null; // todo return bibtex writer
            case RTF:
                return null; // todo return rtf writer
            case TXT:
                return new TxtWriter();
            default:
                throw new IllegalArgumentException();
        }
    }
}
