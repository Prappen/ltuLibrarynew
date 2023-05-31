package com.example.ltulibrary;

import javafx.event.ActionEvent;
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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class returnDvdController implements Initializable {

    @FXML
    private TextField BarcodeDvdButton;

    @FXML
    private Button backbutton;

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
    private void onReturnDvdConfirm() {
        String barcode = BarcodeDvdButton.getText();

        try {
            // Retrieve loan information based on the barcode
            String query = "SELECT barcode_DVD, dvd_Lan_Id FROM dvd_lan WHERE barcode_DVD = ? AND dvd_Status = 'Loaned'";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, barcode);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int loanId = resultSet.getInt("dvd_Lan_Id");
                // Update loan status
                query = "UPDATE dvd_Lan SET dvd_Status = 'Returned' WHERE dvd_Lan_Id = ?";
                statement = connection.prepareStatement(query);
                statement.setInt(1, loanId);
                statement.executeUpdate();


                // Display success message to the customer
                System.out.println("DVD returned successfully.");

                // Clear the barcode text field
                BarcodeDvdButton.clear();
            } else {
                // Display error message indicating an invalid loan or incorrect barcode
                System.out.println("Invalid loan or barcode.");
            }

        } catch (SQLException e) {
            // Handle any potential exceptions
            e.printStackTrace();
        }
    }

    public void onBack(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("startPage.fxml"));
        Parent myPagesParent = fxmlLoader.load();
        Scene myPagesScene = new Scene(myPagesParent);
        Stage currentStage = (Stage) backbutton.getScene().getWindow();
        currentStage.setScene(myPagesScene);
    }
}
