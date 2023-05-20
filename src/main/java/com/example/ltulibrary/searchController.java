package com.example.ltulibrary;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class searchController implements Initializable {
    @FXML
    private TableView<booksearchModel> booktableview;
    @FXML
    private TableColumn<booksearchModel, String> bookname;
    @FXML
    private TableColumn<booksearchModel, Integer> bookyear;
    @FXML
    private TableColumn<booksearchModel, String> bookgenre;
    @FXML
    private TableColumn<booksearchModel, String> bookcategory;
    @FXML
    private TableColumn<booksearchModel, String> bookautor;
    @FXML
    private TableColumn<booksearchModel, Integer> bookamount;
    @FXML
    private TableColumn<booksearchModel, Integer> bookshelf;
    @FXML
    private TableColumn<booksearchModel, String> ISBN;
    @FXML
    private TextField Keywordsbooks;
    @FXML
    private Button Addbook;

    private List<booksearchModel> addedBook = new ArrayList<>();
    private ObservableList<booksearchModel> booksearchModelObservableList = FXCollections.observableArrayList();
    private databaseConnection databaseConnection;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        databaseConnection = new databaseConnection();
        Connection connection;
        try {
            connection = databaseConnection.connect();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        String bookviewQuery = "SELECT barcode_Bok, bok_Namn, bok_Ar, bok_Genre, kategori, bok_Forfattare, Hylla, Antal_Kopior_Inne, ISBN FROM bok";

        try {
            PreparedStatement statement = connection.prepareStatement(bookviewQuery);
            ResultSet queryOutput = statement.executeQuery();
            while (queryOutput.next()) {
                int querybarcodebok = queryOutput.getInt("barcode_Bok");
                String querybokname = queryOutput.getString("bok_Namn");
                int querybookyear = queryOutput.getInt("bok_Ar");
                String querybokgenre = queryOutput.getString("bok_Genre");
                String querybookcategory = queryOutput.getString("kategori");
                String querybookautor = queryOutput.getString("bok_Forfattare");
                int querybookshelf = queryOutput.getInt("Hylla");
                int querybookamount = queryOutput.getInt("Antal_Kopior_Inne");
                String querybookisbn = queryOutput.getString("ISBN");

                booksearchModelObservableList.add(new booksearchModel(querybarcodebok,
                        querybokname, querybookyear, querybokgenre, querybookcategory,
                        querybookautor, querybookshelf, querybookamount, querybookisbn));

            }


            bookname.setCellValueFactory(new PropertyValueFactory<>("bok_namn"));
            bookyear.setCellValueFactory(new PropertyValueFactory<>("bok_Ar"));
            bookgenre.setCellValueFactory(new PropertyValueFactory<>("bok_Genre"));
            bookcategory.setCellValueFactory(new PropertyValueFactory<>("kategori"));
            bookautor.setCellValueFactory(new PropertyValueFactory<>("bok_Forfattare"));
            bookamount.setCellValueFactory(new PropertyValueFactory<>("hylla"));
            bookshelf.setCellValueFactory(new PropertyValueFactory<>("Antal_Kopior_Inne"));
            ISBN.setCellValueFactory(new PropertyValueFactory<>("ISBN"));

            booktableview.setItems(booksearchModelObservableList);

            FilteredList<booksearchModel> filteredData = new FilteredList<>(booksearchModelObservableList, b -> true);

            Keywordsbooks.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredData.setPredicate(booksearchModel -> {
                    if (newValue == null || newValue.isBlank()) {
                        return true;
                    }
                    String searchKeyword = newValue.toLowerCase();
                    return booksearchModel.getBok_namn().toLowerCase().contains(searchKeyword)
                            || booksearchModel.getBok_Forfattare().toLowerCase().contains(searchKeyword)
                            || booksearchModel.getBok_Genre().toLowerCase().contains(searchKeyword)
                            || booksearchModel.getKategori().toLowerCase().contains(searchKeyword);
                });
            });

            SortedList<booksearchModel> sortedData = new SortedList<>(filteredData);
            sortedData.comparatorProperty().bind(booktableview.comparatorProperty());
            booktableview.setItems(sortedData);

        } catch (SQLException e) {
            Logger.getLogger(searchController.class.getName()).log(Level.SEVERE, null, e);
            e.printStackTrace();
        }
    }

    @FXML
    public void AddbookAction(ActionEvent event) {
        Addbook.setOnAction(e -> {
            booksearchModel selectedBook = booktableview.getSelectionModel().getSelectedItem();
            if (selectedBook != null) {
                addedBook.add(selectedBook);
                System.out.println("Item added to cart: " + selectedBook.getBok_namn());
                loanController loanController = new loanController(addedBook);
            } else {
                System.out.println("No item added.");
            }
        });
    }
}
