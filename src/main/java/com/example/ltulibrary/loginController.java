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

public class loginController implements Initializable {

    @FXML
    private TextField username;

    @FXML
    private TextField password;

    @FXML
    private Button loginButton;
    @FXML
    private Button registerButton;


    private Connection connection;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        databaseConnection db = new databaseConnection();

        try {
            db.connect();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @FXML
    public void onLogin(ActionEvent actionEvent) {
        try {
            String enteredUsername = username.getText();
            String enteredPassword = password.getText();

            // Prepare the SQL statement
            String query = "SELECT * FROM Kund WHERE kund_Username = ? AND kund_Password = ?";

            databaseConnection db = new databaseConnection();
            PreparedStatement statement = db.conn.prepareStatement(query);
            statement.setString(1, enteredUsername);
            statement.setString(2, enteredPassword);

            // Execute the query
            ResultSet resultSet = statement.executeQuery();


            if (resultSet.next()) {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("search.fxml"));
                Parent searchParent = fxmlLoader.load();
                Scene searchScene = new Scene(searchParent);
                Stage currentStage = (Stage) loginButton.getScene().getWindow();
                currentStage.setScene(searchScene);

                // Get the instance of the searchController
                searchController searchController = fxmlLoader.getController();

                // Pass the user credentials to the searchController
                searchController.setUserCredentials(enteredUsername, enteredPassword);

                int kund_Id = resultSet.getInt("kund_Id");
                String name_F = resultSet.getString("namn_F");
                String name_E = resultSet.getString("namn_E");
                String email = resultSet.getString("epost");
                String phone = resultSet.getString("telefon_Nr");
                String address = resultSet.getString("adress");
                int age = resultSet.getInt("age");
                String kund_Typ = resultSet.getString("kund_Typ");

                searchController.setUserDetails(kund_Id,name_F, name_E, email, phone, address, age, kund_Typ);
            } else {
                System.out.println("Invalid credentials");
                // Credentials not found or do not match
                // Add your code here to display an error message or take appropriate action
            }
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void onRegister(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("register.fxml"));
            Parent loginPageParent = loader.load();
            Stage stage = (Stage) registerButton.getScene().getWindow();
            stage.setScene(new Scene(loginPageParent));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}