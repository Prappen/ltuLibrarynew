package com.example.ltulibrary;

import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.input.DragEvent;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class hanteraController implements Initializable {

    private StringBuilder emailAddresses; // Variable to store the email addresses

    public TextArea mailListaTextArea; // The TextArea variable with the correct fx:id

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            databaseConnection.connect();
            Connection connect = databaseConnection.conn;  // Retrieve the connection object from databaseConnection

            // Execute a query to retrieve the email addresses for delayed loans
            String query = "SELECT kund.epost " +
                    "FROM kund " +
                    "INNER JOIN Lan ON kund.kund_Id = Lan.kund_Id " +
                    "LEFT JOIN Bok_Lan ON Lan.lan_Id = Bok_Lan.lan_Id " +
                    "LEFT JOIN DVD_Lan ON Lan.lan_Id = DVD_Lan.lan_Id " +
                    "WHERE (Bok_Lan.return_Date < CURRENT_DATE() OR DVD_Lan.dvdreturn_Date < CURRENT_DATE())";

            Statement statement = connect.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            // Iterate through the result set and append email addresses to the StringBuilder
            emailAddresses = new StringBuilder();
            while (resultSet.next()) {
                String emailAddress = resultSet.getString("epost");
                emailAddresses.append(emailAddress).append("\n");
            }

            resultSet.close();
            statement.close();

            System.out.println("Retrieved email addresses: " + emailAddresses.toString()); // Debug statement

            if (mailListaTextArea != null) {
                System.out.println("TextArea found"); // Debug statement
                mailListaTextArea.setText(emailAddresses.toString()); // Set the email addresses in the TextArea
            } else {
                System.out.println("TextArea not found"); // Debug statement
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle any potential exceptions according to your application's requirements
        }
    }

    public void onMailLista(DragEvent event) {
        if (emailAddresses != null) {
            mailListaTextArea.setText(emailAddresses.toString()); // Set the email addresses in the TextArea
        }
    }
}
