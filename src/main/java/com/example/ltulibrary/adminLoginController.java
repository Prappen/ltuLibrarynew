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

public class adminLoginController implements Initializable {

    @FXML
    private TextField adminNameTextField;

    @FXML
    private TextField adminPasswordTextField;

    @FXML
    private Button adminLoginButton;


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
    public void OnAdminLogin(ActionEvent actionEvent) {
        try {
            String username = adminNameTextField.getText();
            String password = adminPasswordTextField.getText();

            // Prepare the SQL statement
            String query = "SELECT a_Password AS password, a_Id AS username FROM Personal WHERE a_Id = ? AND a_Password = ?";

            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, username);
            statement.setString(2, password);

            // Execute the query
            ResultSet resultSet = statement.executeQuery();

            // Check if a matching user was found
            if (resultSet.next()) {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("adminPage.fxml"));
                Parent myPagesParent = fxmlLoader.load();
                Scene myPagesScene = new Scene(myPagesParent);
                Stage currentStage = (Stage) adminLoginButton.getScene().getWindow();
                currentStage.setScene(myPagesScene);
            } else {
                System.out.println("Invalid credentials");
                // Credentials not found or do not match
                // Add your code here to display an error message or take appropriate action
            }

            // Close the resources
            resultSet.close();
            statement.close();
        } catch (SQLException | IOException e) {
            e.printStackTrace();
            // Handle the exception appropriately
        }
    }

    public void adminNameOnAction(ActionEvent actionEvent) {
    }

    public void adminPasswordOnAction(ActionEvent actionEvent) {
    }
}
