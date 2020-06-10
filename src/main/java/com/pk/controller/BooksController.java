package com.pk.controller;

import com.pk.model.Book;
import com.pk.reader.BooksReaderFactory;
import com.pk.writer.BooksWriterFactory;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.FileChooser;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.LocalDateStringConverter;

import java.io.File;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Controller of books view.
 */
public class BooksController implements Initializable {
    public TableView<Book> booksTable;
    public TableColumn<Book, String> authorColumn;
    public TableColumn<Book, String> titleColumn;
    public TableColumn<Book, String> genreColumn;
    public TableColumn<Book, String> publisherColumn;
    public TableColumn<Book, LocalDate> publishedColumn;
    public TableColumn<Book, Double> priceColumn;
    public TableColumn<Book, String> descriptionColumn;
    public TextField publisherInput;
    public TextField genreInput;
    public TextField titleInput;
    public TextField authorInput;
    public TextArea descriptionInput;
    public Spinner<Double> priceInput;
    public DatePicker publishedDatePicker;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initPriceSpinner();
        initTable();
    }

    /**
     * Initialize column
     * @param column Column that will be initialized.
     * @param propertyName Property name of column.
     */
    private void initColumn(TableColumn<Book, ?> column, String propertyName) {
        column.setCellValueFactory(new PropertyValueFactory<>(propertyName));
    }

    /**
     * Initialize simple column, that contains String
     * @param column Column that will be initialized.
     * @param propertyName Property name of column.
     * @param eventHandler Event that will be called after updating data.
     */
    private void initSimpleStringColumn(TableColumn<Book, String> column, String propertyName,
                                        EventHandler<TableColumn.CellEditEvent<Book, String>> eventHandler) {
        initColumn(column, propertyName);
        column.setCellFactory(TextFieldTableCell.forTableColumn());
        column.setOnEditCommit(eventHandler);
    }

    /**
     * Table key listener. Removes row after pressing delete key.
     * @param keyEvent Contains info about pressed key.
     */
    public void tableKeyPressedListener(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.DELETE) {
            Book selectedItem = booksTable.getSelectionModel().getSelectedItem();
            booksTable.getItems().remove(selectedItem);
        }
    }

    /**
     * Initialize table
     */
    private void initTable() {
        initSimpleStringColumn(authorColumn, "author", e -> {
            Book book = e.getTableView().getItems().get(e.getTablePosition().getRow());
            book.setAuthor(e.getNewValue());
        });
        initSimpleStringColumn(titleColumn, "title", e -> {
            Book book = e.getTableView().getItems().get(e.getTablePosition().getRow());
            book.setTitle(e.getNewValue());
        });
        initSimpleStringColumn(genreColumn, "genre", e -> {
            Book book = e.getTableView().getItems().get(e.getTablePosition().getRow());
            book.setGenre(e.getNewValue());
        });
        initSimpleStringColumn(publisherColumn, "publisher", e -> {
            Book book = e.getTableView().getItems().get(e.getTablePosition().getRow());
            book.setPublisher(e.getNewValue());
        });
        initSimpleStringColumn(descriptionColumn, "description", e -> {
            Book book = e.getTableView().getItems().get(e.getTablePosition().getRow());
            book.setDescription(e.getNewValue());
        });

        initColumn(priceColumn, "price");
        priceColumn.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        priceColumn.setOnEditCommit(e -> {
            Book book = e.getTableView().getItems().get(e.getTablePosition().getRow());
            if (e.getNewValue() > 0) {
                double price = Math.floor(e.getNewValue() * 100.0) / 100.0;
                book.setPrice(price);
            }
            booksTable.refresh();
        });

        initColumn(publishedColumn, "publishedDate");
        publishedColumn.setCellFactory(TextFieldTableCell.forTableColumn(new LocalDateStringConverter()));
        publishedColumn.setOnEditCommit(e -> {
            Book book = e.getTableView().getItems().get(e.getTablePosition().getRow());
            book.setPublishedDate(e.getNewValue());
        });

        booksTable.setEditable(true);
    }

    /**
     * Initialize spinner that represents price of book.
     */
    private void initPriceSpinner() {
        SpinnerValueFactory.DoubleSpinnerValueFactory valueFactory = new SpinnerValueFactory
                .DoubleSpinnerValueFactory(0.01, Short.MAX_VALUE, 10.0, 0.5);
        priceInput.setValueFactory(valueFactory);

        priceInput.getEditor().textProperty().addListener((observableValue, oldValue, newValue) -> {
            if (!newValue.matches("\\d*(,\\d*)?")) {
                priceInput.getEditor().setText(oldValue);
            }
        });

        priceInput.focusedProperty().addListener((s, ov, nv) -> {
            if (nv) return;
            priceInput.commitValue();
        });
    }


    /**
     * Opens file chooser where you can choose xml file that contains books. Books will be added to current books.
     */
    public void loadBooksButton() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("XML", "*.xml"));
        File file = fileChooser.showOpenDialog(booksTable.getScene().getWindow());
        if (file != null) {
            try {
                List<Book> newBooks = BooksReaderFactory.getBooksReader(BooksReaderFactory.XML).read(file.getPath());
                booksTable.getItems().addAll(newBooks);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Saves books to file.
     * @param fileType Format of file.
     */
    private void saveBooks(int fileType) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(BooksWriterFactory.getExtensionsFilter(fileType));
        File file = fileChooser.showSaveDialog(booksTable.getScene().getWindow());
        if (file != null) {
            String path = file.getPath();
            System.out.println(path);
            ObservableList<Book> books = booksTable.getItems();
            BooksWriterFactory.getBooksWriter(fileType).write(books.toArray(new Book[books.size()]), path);
        }
    }

    /**
     * Saves books to rtf file.
     */
    public void saveRtf() {
        saveBooks(BooksWriterFactory.RTF);
    }

    /**
     * Saves books to bibtex file.
     */
    public void saveBibTeX() {
        saveBooks(BooksWriterFactory.BIBTEX);
    }

    /**
     * Saves books to txt file.
     */
    public void savePlainText() {
        saveBooks(BooksWriterFactory.TXT);
    }

    /**
     * Removes all books.
     */
    public void reset() {
        String msg = "Do you want to remove all books?";
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, msg, ButtonType.YES, ButtonType.NO);
        alert.showAndWait();

        if (alert.getResult() == ButtonType.YES) {
            booksTable.getItems().clear();
        }
    }

    /**
     * Adds new book to table. Gets data from inputs.
     */
    public void addNewBook() {
        Book book = Book.builder()
                .author(authorInput.getText())
                .description(descriptionInput.getText())
                .genre(genreInput.getText())
                .title(titleInput.getText())
                .publisher(publisherInput.getText())
                .price(priceInput.getValue())
                .publishedDate(publishedDatePicker.getValue())
                .build();
        booksTable.getItems().add(book);
        authorInput.setText("");
        descriptionInput.setText("");
        genreInput.setText("");
        titleInput.setText("");
        publisherInput.setText("");
    }
}