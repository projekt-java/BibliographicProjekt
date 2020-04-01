package com.pk.reader;

/**
 * Returns specific {@link BooksReader}.
 */
public class BooksReaderFactory {
    public static final int XML = 0;

    /**
     * <p>Returns {@link BooksReader}. You can choose specific reader by giving one of the constants that represents
     * format of data:</p>
     * <p>{@link #XML}</p>
     * <p>If you give wrong argument {@link IllegalArgumentException} will be thrown.</p>
     * @param format Format of data.
     * @return Specific {@link BooksReader}
     */
    public static BooksReader getBooksReader(int format) {
        switch (format) {
            case XML:
                return new XmlReader();
            default:
                throw new IllegalArgumentException();
        }
    }
}
