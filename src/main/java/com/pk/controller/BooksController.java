package com.pk.controller;

import com.pk.model.Book;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.LocalDateStringConverter;

import java.time.LocalDate;

/**
 * Controller of books view.
 */
public class BooksController {
    public TableView<Book> booksTable;
    public TableColumn<Book, String> authorColumn;
    public TableColumn<Book, String> titleColumn;
    public TableColumn<Book, String> genreColumn;
    public TableColumn<Book, String> publisherColumn;
    public TableColumn<Book, LocalDate> publishedColumn;
    public TableColumn<Book, Double> priceColumn;
    public TableColumn<Book, String> descriptionColumn;

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
            book.setPrice(e.getNewValue());
        });

        initColumn(publishedColumn, "publishedDate");
        publishedColumn.setCellFactory(TextFieldTableCell.forTableColumn(new LocalDateStringConverter()));
        publishedColumn.setOnEditCommit(e -> {
            Book book = e.getTableView().getItems().get(e.getTablePosition().getRow());
            book.setPublishedDate(e.getNewValue());
        });

        booksTable.setEditable(true);
    }

    @FXML
    void initialize() {
        initTable();

        // todo temporary books
        Book book = Book.builder()
                .author("author 1")
                .description("Some description 1")
                .genre("genre 1")
                .price(12.12)
                .publishedDate(LocalDate.now())
                .publisher("Publisher 1")
                .title("Some title 1")
                .build();
        booksTable.getItems().add(book);

        book = Book.builder()
                .author("author 2")
                .description("Some description 2")
                .genre("genre 2")
                .price(12.12)
                .publishedDate(LocalDate.now())
                .publisher("Publisher 2")
                .title("Another title 2")
                .build();
        booksTable.getItems().add(book);
    }
}