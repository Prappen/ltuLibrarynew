package com.example.ltulibrary;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class adminBookController implements Initializable {

    @FXML
    private TextField barcode_Bok;

    @FXML
    private TextField bok_Namn;

    @FXML
    private TextField bok_Ar;

    @FXML
    private TextField bok_Genre;

    @FXML
    private TextField kategori;

    @FXML
    private TextField bok_Forfattare;

    @FXML
    private TextField Hylla;

    @FXML
    private TextField Antal_kopior_Inne;

    @FXML
    private TextField ISBN;

    @FXML
    private Button addBookButton;

    @FXML
    private Button deleteBookButton;

    @FXML
    private Button returnFromBookButton;

    private Connection connection;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        databaseConnection db = new databaseConnection();

        try {
            db.connect();
            connection = db.conn;  // Assign the connection object from databaseConnection
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void onAddBook() {
        String barcode = barcode_Bok.getText();
        String title = bok_Namn.getText();
        String year = bok_Ar.getText();
        String genre = bok_Genre.getText();
        String category = kategori.getText();
        String author = bok_Forfattare.getText();
        String shelf = Hylla.getText();
        String numCopies = Antal_kopior_Inne.getText();
        String isbn = ISBN.getText();

        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO Bok (barcode_Bok, bok_Namn, bok_Ar, bok_Genre, kategori, bok_Forfattare, Hylla, Antal_kopior_Inne, ISBN) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
            statement.setString(1, barcode);
            statement.setString(2, title);
            statement.setString(3, year);
            statement.setString(4, genre);
            statement.setString(5, category);
            statement.setString(6, author);
            statement.setString(7, shelf);
            statement.setString(8, numCopies);
            statement.setString(9, isbn);
            statement.executeUpdate();
            System.out.println("Book added successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void onDeleteBook() {
        String barcode = barcode_Bok.getText();

        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM Bok WHERE barcode_Bok = ?");
            statement.setString(1, barcode);
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Book deleted successfully.");
            } else {
                System.out.println("Book with barcode " + barcode + " not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void onReturnFromBook() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("adminPage.fxml"));
        Parent adminPageParent = fxmlLoader.load();
        Scene adminPageScene = new Scene(adminPageParent);
        Stage currentStage = (Stage) returnFromBookButton.getScene().getWindow();
        currentStage.setScene(adminPageScene);
    }
}