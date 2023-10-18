package com.example.library;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class LibraryManagementSystem extends Application {

    private static final String DATA_FILE = "library_data.txt";
    private ObservableList<Book> libraryData;
    private TableView<Book> tableView;

    public static void main(String[] args) {
        launch(args);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Library Management System");

        tableView = new TableView<>();
        Button addButton = new Button("Add");
        Button editButton = new Button("Edit");
        Button deleteButton = new Button("Delete");

        TableColumn<Book, String> titleColumn = new TableColumn<>("Title");
        titleColumn.setCellValueFactory(cellData -> cellData.getValue().titleProperty());

        TableColumn<Book, String> authorColumn = new TableColumn<>("Author");
        authorColumn.setCellValueFactory(cellData -> cellData.getValue().authorProperty());

        tableView.getColumns().addAll(titleColumn, authorColumn);

        addButton.setOnAction(e -> showAddDialog());
        editButton.setOnAction(e -> showEditDialog());
        deleteButton.setOnAction(e -> deleteSelectedBook());

        VBox layout = new VBox();
        layout.setSpacing(10);
        layout.setPadding(new Insets(10));
        layout.getChildren().addAll(tableView, addButton, editButton, deleteButton);

        libraryData = FXCollections.observableArrayList(readLibraryDataFromFile());

        tableView.setItems(libraryData);

        Scene scene = new Scene(layout, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void showAddDialog() {
        Dialog<Book> dialog = new Dialog<>();
        dialog.setTitle("Add Book");
        dialog.setHeaderText("Enter book details");

        // Create UI components for the dialog
        TextField titleField = new TextField();
        titleField.setPromptText("Title");
        TextField authorField = new TextField();
        authorField.setPromptText("Author");

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));
        grid.add(new Label("Title:"), 0, 0);
        grid.add(titleField, 1, 0);
        grid.add(new Label("Author:"), 0, 1);
        grid.add(authorField, 1, 1);

        dialog.getDialogPane().setContent(grid);

        ButtonType addButton = new ButtonType("Add", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(addButton, ButtonType.CANCEL);

        dialog.setResultConverter(buttonType -> {
            if (buttonType == addButton) {
                String title = titleField.getText();
                String author = authorField.getText();
                if (!title.isEmpty() && !author.isEmpty()) {
                    return new Book(title, author);
                }
            }
            return null;
        });

        dialog.showAndWait().ifPresent(newBook -> {
            libraryData.add(newBook);
            saveLibraryDataToFile();
        });
    }

    private void showEditDialog() {
        Book selectedBook = tableView.getSelectionModel().getSelectedItem();
        if (selectedBook != null) {
            Dialog<Book> dialog = new Dialog<>();
            dialog.setTitle("Edit Book");
            dialog.setHeaderText("Edit book details");

            TextField titleField = new TextField(selectedBook.getTitle());
            TextField authorField = new TextField(selectedBook.getAuthor());

            GridPane grid = new GridPane();
            grid.setHgap(10);
            grid.setVgap(10);
            grid.setPadding(new Insets(20, 150, 10, 10));
            grid.add(new Label("Title:"), 0, 0);
            grid.add(titleField, 1, 0);
            grid.add(new Label("Author:"), 0, 1);
            grid.add(authorField, 1, 1);

            dialog.getDialogPane().setContent(grid);

            ButtonType saveButton = new ButtonType("Save", ButtonBar.ButtonData.OK_DONE);
            dialog.getDialogPane().getButtonTypes().addAll(saveButton, ButtonType.CANCEL);

            dialog.setResultConverter(buttonType -> {
                if (buttonType == saveButton) {
                    String title = titleField.getText();
                    String author = authorField.getText();
                    if (!title.isEmpty() && !author.isEmpty()) {
                        selectedBook.setTitle(title);
                        selectedBook.setAuthor(author);
                        return selectedBook;
                    }
                }
                return null;
            });

            dialog.showAndWait().ifPresent(updatedBook -> {
                tableView.refresh();
                saveLibraryDataToFile();
            });
        }
    }

    private void deleteSelectedBook() {
        Book selectedBook = tableView.getSelectionModel().getSelectedItem();
        if (selectedBook != null) {
            libraryData.remove(selectedBook);
            saveLibraryDataToFile();
        }
    }

    private List<Book> readLibraryDataFromFile() {
        List<Book> libraryData = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(DATA_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields.length >= 2) {
                    String title = fields[0];
                    String author = fields[1];
                    libraryData.add(new Book(title, author));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return libraryData;
    }

    private void saveLibraryDataToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(DATA_FILE))) {
            for (Book book : libraryData) {
                writer.write(book.getTitle() + "," + book.getAuthor());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
