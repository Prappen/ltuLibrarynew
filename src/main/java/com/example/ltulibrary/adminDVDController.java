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

public class adminDVDController implements Initializable {

    @FXML
    private TextField barcode_DVD;

    @FXML
    private TextField dvd_Namn;

    @FXML
    private TextField dvd_Ar;

    @FXML
    private TextField aldersgrans;

    @FXML
    private TextField dvd_Genre;

    @FXML
    private TextField dvd_Regissor;

    @FXML
    private TextField Hylla;

    @FXML
    private TextField Antal_kopior_Inne;

    @FXML
    private Button addDvdButton;

    @FXML
    private Button deleteDvdButton;

    @FXML
    private Button returnToAdminButton;
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
    public void onAddDvd() {
        String barcode = barcode_DVD.getText();
        String title = dvd_Namn.getText();
        String year = dvd_Ar.getText();
        String ageLimit = aldersgrans.getText();
        String genre = dvd_Genre.getText();
        String director = dvd_Regissor.getText();
        String shelf = Hylla.getText();
        String numCopies = Antal_kopior_Inne.getText();

        try {

            PreparedStatement statement = connection.prepareStatement("INSERT INTO DVD (barcode_DVD, dvd_Namn, dvd_Ar, aldersgrans, dvd_Genre, dvd_Regissor, Hylla, Antal_kopior_Inne) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
            statement.setString(1, barcode);
            statement.setString(2, title);
            statement.setString(3, year);
            statement.setString(4, ageLimit);
            statement.setString(5, genre);
            statement.setString(6, director);
            statement.setString(7, shelf);
            statement.setString(8, numCopies);
            statement.executeUpdate();
            System.out.println("DVD added successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void onDeleteDvd() {
        String barcode = barcode_DVD.getText();

        try {

            PreparedStatement statement = connection.prepareStatement("DELETE FROM DVD WHERE barcode_DVD = ?");
            statement.setString(1, barcode);
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("DVD deleted successfully.");
            } else {
                System.out.println("DVD with barcode " + barcode + " not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void onReturnToAdmin() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("adminPage.fxml"));
        Parent adminPageParent = fxmlLoader.load();
        Scene adminPageScene = new Scene(adminPageParent);
        Stage currentStage = (Stage) returnToAdminButton.getScene().getWindow();
        currentStage.setScene(adminPageScene);
    }
}