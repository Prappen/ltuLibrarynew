package com.example.ltulibrary;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class registerController implements Initializable {

    @FXML
    private TextField firstNameTextField;

    @FXML
    private TextField lastNameTextField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button createButton;

    @FXML
    private TextField mainTextField;

    @FXML
    private TextField phoneTextField;

    @FXML
    private TextField ageTextField;

    @FXML
    private TextField kundTypTextField;

    @FXML
    private TextField usernameTextField;

    @FXML
    private TextField adressTextField;

    @FXML
    private Button returnToLoginButton;
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
    private void onCreateButton() {
        String firstName = firstNameTextField.getText();
        String lastName = lastNameTextField.getText();
        String password = passwordField.getText();
        String email = mainTextField.getText();
        String phoneNumber = phoneTextField.getText();
        String age = ageTextField.getText();
        String kundTyp = kundTypTextField.getText();
        String username = usernameTextField.getText();
        String address = adressTextField.getText();

        // Validate the input as needed

        try {
            PreparedStatement statement = databaseConnection.conn.prepareStatement("INSERT INTO kund (namn_F, namn_E, kund_Password, epost, telefon_Nr, age, kund_Typ, kund_Username, adress) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, firstName);
            statement.setString(2, lastName);
            statement.setString(3, password);
            statement.setString(4, email);
            statement.setString(5, phoneNumber);
            statement.setString(6, age);
            statement.setString(7, kundTyp);
            statement.setString(8, username);
            statement.setString(9, address);
            statement.executeUpdate();

            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                int userId = generatedKeys.getInt(1);
                System.out.println("User registered successfully with ID: " + userId);

                // Redirect to loanPage.fxml
                FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
                Parent loanPageParent = loader.load();
                Stage stage = (Stage) createButton.getScene().getWindow();
                stage.setScene(new Scene(loanPageParent));
                stage.show();
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onReturnToLogin() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
            Parent loginPageParent = loader.load();
            Stage stage = (Stage) returnToLoginButton.getScene().getWindow();
            stage.setScene(new Scene(loginPageParent));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}

