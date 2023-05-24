package com.example.ltulibrary;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class returnBookController implements Initializable {

    @FXML
    private TextField BarcodeBookButton;

    @FXML
    private Button returnBookComfirmButton;

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
    private void onReturnBookComfirm() {
        String barcode = BarcodeBookButton.getText();

        try {
            // Retrieve loan information based on the barcode
            String query = "SELECT barcode_Bok, bok_Lan_Id FROM Bok_lan WHERE barcode_Bok = ? AND bok_Status = 'Loaned'";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, barcode);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int loanId = resultSet.getInt("bok_Lan_Id");


                // Update loan status and return date
                query = "UPDATE Bok_lan SET bok_Status = 'Returned' WHERE bok_Lan_Id = ?";
                statement = connection.prepareStatement(query);
                statement.setInt(1, loanId);
                statement.executeUpdate();


                // Display success message to the customer
                System.out.println("Book returned successfully.");

                // Clear the barcode text field
                BarcodeBookButton.clear();
            } else {
                // Display error message indicating an invalid loan or incorrect barcode
                System.out.println("Invalid loan or barcode.");
            }

        } catch (SQLException e) {
            // Handle any potential exceptions
            e.printStackTrace();
        }
    }

    // Other methods, if any...
}