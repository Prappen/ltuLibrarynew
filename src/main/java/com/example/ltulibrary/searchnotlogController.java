package com.example.ltulibrary;

import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Date;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;

public class searchnotlogController implements Initializable {

    private List<booksearchModel> cartBooks = new ArrayList<>();
    private List<dvdsearchModel> cartDVDs = new ArrayList<>();
    @FXML
    private TableView<dvdsearchModel> dvdTableView;

    @FXML
    private TableColumn<dvdsearchModel, Integer> dvdBarcodeColumn;

    @FXML
    private TableColumn<dvdsearchModel, String> dvdNameColumn;

    @FXML
    private TableColumn<dvdsearchModel, String> dvdGenreColumn;

    @FXML
    private TableColumn<dvdsearchModel, String> dvdDirectorColumn;

    @FXML
    private TableColumn<dvdsearchModel, Integer> dvdAgeRatingColumn;

    @FXML
    private TableColumn<dvdsearchModel, Integer> dvdShelfColumn;

    @FXML
    private TableColumn<dvdsearchModel, Integer> dvdCopiesColumn;

    @FXML
    private TableView<booksearchModel> bookTableView;

    @FXML
    private TableColumn<booksearchModel, Integer> bookBarcodeColumn;

    @FXML
    private TableColumn<booksearchModel, String> bookNameColumn;

    @FXML
    private TableColumn<booksearchModel, Integer> bookYearColumn;

    @FXML
    private TableColumn<booksearchModel, String> bookGenreColumn;

    @FXML
    private TableColumn<booksearchModel, String> bookCategoryColumn;

    @FXML
    private TableColumn<booksearchModel, String> bookAuthorColumn;

    @FXML
    private TableColumn<booksearchModel, Integer> bookShelfColumn;

    @FXML
    private TableColumn<booksearchModel, Integer> bookCopiesColumn;

    @FXML
    private TableColumn<booksearchModel, String> bookISBNColumn;

    @FXML
    private TextField keywordsField;
    @FXML
    private Button login;
    @FXML
    private Button Cancelsearch;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        databaseConnection db = new databaseConnection();

        try {
            db.connect();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        String dvdViewQuery = "SELECT barcode_DVD, dvd_Namn, dvd_Ar, dvd_Genre, dvd_Regissor, aldersgrans, Hylla, Antal_Kopior_Inne FROM DVD";

        try {
            PreparedStatement dvdStatement = db.conn.prepareStatement(dvdViewQuery);
            ResultSet dvdResultSet = dvdStatement.executeQuery();

            while (dvdResultSet.next()) {
                int barcode = dvdResultSet.getInt("barcode_DVD");
                String name = dvdResultSet.getString("dvd_Namn");
                int year = dvdResultSet.getInt("dvd_Ar");
                String genre = dvdResultSet.getString("dvd_Genre");
                String director = dvdResultSet.getString("dvd_Regissor");
                int ageRating = dvdResultSet.getInt("aldersgrans");
                int shelf = dvdResultSet.getInt("Hylla");
                int copies = dvdResultSet.getInt("Antal_Kopior_Inne");

                if (dvdTableView != null) {
                    dvdsearchModel dvd = new dvdsearchModel(barcode, name, year, genre, director, ageRating, shelf, copies);
                    dvdTableView.getItems().add(dvd);
                } else {
                    System.out.println("dvdTableView is null. Unable to add DVD to the table.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String bookViewQuery = "SELECT barcode_Bok, bok_Namn, bok_Ar, bok_Genre, kategori, bok_Forfattare, Hylla, Antal_Kopior_Inne, ISBN FROM Bok";

        try {
            PreparedStatement bookStatement = db.conn.prepareStatement(bookViewQuery);
            ResultSet bookResultSet = bookStatement.executeQuery();

            while (bookResultSet.next()) {
                int barcode = bookResultSet.getInt("barcode_Bok");
                String name = bookResultSet.getString("bok_Namn");
                int year = bookResultSet.getInt("bok_Ar");
                String genre = bookResultSet.getString("bok_Genre");
                String category = bookResultSet.getString("kategori");
                String author = bookResultSet.getString("bok_Forfattare");
                int shelf = bookResultSet.getInt("Hylla");
                int copies = bookResultSet.getInt("Antal_Kopior_Inne");
                String isbn = bookResultSet.getString("ISBN");

                if (bookTableView != null) {
                    booksearchModel book = new booksearchModel(barcode, name, year, genre, category, author, shelf, copies, isbn);
                    bookTableView.getItems().add(book);
                } else {
                    System.out.println("bookTableView is null. Unable to add book to the table.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Initialize DVD table columns
        dvdNameColumn.setCellValueFactory(data -> data.getValue().nameProperty());
        dvdGenreColumn.setCellValueFactory(data -> data.getValue().genreProperty());
        dvdDirectorColumn.setCellValueFactory(data -> data.getValue().directorProperty());
        dvdAgeRatingColumn.setCellValueFactory(data -> data.getValue().ageRatingProperty());
        dvdShelfColumn.setCellValueFactory(data -> data.getValue().shelfProperty());
        dvdCopiesColumn.setCellValueFactory(data -> data.getValue().copiesProperty());

        // Initialize Book table columns
        bookNameColumn.setCellValueFactory(data -> data.getValue().nameProperty());
        bookYearColumn.setCellValueFactory(data -> data.getValue().yearProperty());
        bookGenreColumn.setCellValueFactory(data -> data.getValue().genreProperty());
        bookCategoryColumn.setCellValueFactory(data -> data.getValue().kategoriProperty());
        bookAuthorColumn.setCellValueFactory(data -> data.getValue().authorProperty());
        bookShelfColumn.setCellValueFactory(data -> data.getValue().shelfProperty());
        bookCopiesColumn.setCellValueFactory(data -> data.getValue().copiesProperty());
        bookISBNColumn.setCellValueFactory(data -> data.getValue().isbnProperty());

        // Create filtered lists for books and DVDs
        FilteredList<booksearchModel> bookFilteredData = new FilteredList<>(bookTableView.getItems(), b -> true);
        FilteredList<dvdsearchModel> dvdFilteredData = new FilteredList<>(dvdTableView.getItems(), d -> true);

        // Bind the sorted lists to the table views
        SortedList<booksearchModel> sortedBookData = new SortedList<>(bookFilteredData);
        SortedList<dvdsearchModel> sortedDVDData = new SortedList<>(dvdFilteredData);
        sortedBookData.comparatorProperty().bind(bookTableView.comparatorProperty());
        sortedDVDData.comparatorProperty().bind(dvdTableView.comparatorProperty());
        bookTableView.setItems(sortedBookData);
        dvdTableView.setItems(sortedDVDData);

        // Listen to changes in the search keywords text field
        keywordsField.textProperty().addListener((observable, oldValue, newValue) -> {
            String keywords = newValue.toLowerCase();
            bookFilteredData.setPredicate(booksearchModel -> {
                if (keywords == null || keywords.isBlank()) {
                    return true;
                }
                return booksearchModel.getBok_namn().toLowerCase().contains(keywords)
                        || booksearchModel.getBok_Forfattare().toLowerCase().contains(keywords)
                        || booksearchModel.getBok_Genre().toLowerCase().contains(keywords)
                        || booksearchModel.getKategori().toLowerCase().contains(keywords)
                        || booksearchModel.getISBN().toLowerCase().contains(keywords);
            });
            dvdFilteredData.setPredicate(dvdsearchModel -> {
                if (keywords == null || keywords.isBlank()) {
                    return true;
                }
                return dvdsearchModel.getDvd_Namn().toLowerCase().contains(keywords)
                        || dvdsearchModel.getDvd_Regissor().toLowerCase().contains(keywords)
                        || dvdsearchModel.getDvd_Genre().toLowerCase().contains(keywords)
                        || dvdsearchModel.getDvd_Namn().toLowerCase().contains(keywords);
            });
        });
    }

    public void loginAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("login.fxml"));
        Parent myPagesParent = fxmlLoader.load();
        Scene myPagesScene = new Scene(myPagesParent);
        Stage currentStage = (Stage) login.getScene().getWindow();
        currentStage.setScene(myPagesScene);
    }

    public void Cancelsearch(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("startPage.fxml"));
        Parent myPagesParent = fxmlLoader.load();
        Scene myPagesScene = new Scene(myPagesParent);
        Stage currentStage = (Stage) Cancelsearch.getScene().getWindow();
        currentStage.setScene(myPagesScene);
    }
}
