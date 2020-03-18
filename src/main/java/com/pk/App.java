package com.pk;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Runs the entire application. Sets the first view.
 */
public class App extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        // getting fxml view file
        Parent root = FXMLLoader.load(getClass().getResource("/view/booksView.fxml"));

        // adding stylesheet to root
        root.getStylesheets().add(getClass().getResource("/style/style.css").toExternalForm());
        stage.setTitle("Bibliographic App");
        stage.setScene(new Scene(root, 1000, 700));
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}