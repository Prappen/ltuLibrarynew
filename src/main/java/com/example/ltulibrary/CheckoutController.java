package com.example.ltulibrary;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class CheckoutController implements Initializable {


    @FXML
    Button Loggout;
    @FXML
    private TextArea TextArea;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        databaseConnection db = new databaseConnection();

        try {
            db.connect();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        // Generate receipt
        generateReceipt(db);
    }

    private void generateReceipt(databaseConnection db) {
        try {
            // Create the SQL query to retrieve the newest added loan and Kund information
            String sql = "SELECT Kund.namn_F, Kund.namn_E, Kund.telefon_Nr, Kund.epost, Lan.lan_Id, Bok_Lan.datum_Utlanad, Bok_Lan.return_Date, Bok.barcode_Bok, DVD_Lan.dvddatum_Utlanad, DVD_Lan.dvdreturn_Date, DVD.barcode_DVD " +
                    "FROM Kund " +
                    "JOIN Lan ON Kund.kund_Id = Lan.kund_Id " +
                    "LEFT JOIN Bok_Lan ON Lan.lan_Id = Bok_Lan.lan_Id " +
                    "LEFT JOIN Bok ON Bok_Lan.barcode_Bok = Bok.barcode_Bok " +
                    "LEFT JOIN DVD_Lan ON Lan.lan_Id = DVD_Lan.lan_Id " +
                    "LEFT JOIN DVD ON DVD_Lan.barcode_DVD = DVD.barcode_DVD " +
                    "ORDER BY Lan.lan_Id DESC " +
                    "LIMIT 1";

            // Execute the query
            Statement statement = db.conn.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery(sql);

            // Check if there is a result
            if (resultSet.next()) {
                // Retrieve the necessary information from the result set
                int lanId = resultSet.getInt("lan_Id");
                java.sql.Date datumUtlanad = resultSet.getDate("datum_Utlanad");
                java.sql.Date returnDate = resultSet.getDate("return_date");
                String barcodeBok = resultSet.getString("barcode_Bok");
                java.sql.Date dvdDatumUtlanad = resultSet.getDate("dvddatum_Utlanad");
                java.sql.Date dvdReturnDate = resultSet.getDate("dvdreturn_date");
                String barcodeDVD = resultSet.getString("barcode_DVD");
                String namnF = resultSet.getString("namn_F");
                String namnE = resultSet.getString("namn_E");
                String telefonNr = resultSet.getString("telefon_Nr");

                // Create the receipt message
                String receiptMessage =
                        "Receipt\n" +
                                "Customer Name (First): " + namnF + " " + namnE + "\n" +
                                "Loan ID: " + lanId + "\n" +
                                "Book Loan Date: " + datumUtlanad + "\n" +
                                "Book Return Date: " + returnDate + "\n" +
                                "Book Barcode: " + barcodeBok + "\n" +
                                "DVD Loan Date: " + dvdDatumUtlanad + "\n" +
                                "DVD Return Date: " + dvdReturnDate + "\n" +
                                "DVD Barcode: " + barcodeDVD + "\n" +
                                "Telephone Number: " + telefonNr;

                // Set the receipt message in the text area
                TextArea.setText(receiptMessage);
            }

            // Close the result set and statement
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception appropriately, e.g., display an error message
        }
    }

    public void Loggout(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("startPage.fxml"));
            Parent myPagesParent = fxmlLoader.load();
            Scene myPagesScene = new Scene(myPagesParent);
            Stage currentStage = (Stage) Loggout.getScene().getWindow();
            currentStage.setScene(myPagesScene);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}