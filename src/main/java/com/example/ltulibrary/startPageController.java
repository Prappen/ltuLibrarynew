package com.example.ltulibrary;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class startPageController  implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//kopiera dessa två
    }

    @FXML
    private Button cancelButton;
    @FXML
    private Button loginbutton;
    @FXML
    private Button searchButton;
    @FXML
    private Button adminButton;
    @FXML
    private Button ReturnBook1;
    @FXML
    private Button returnDvd1;


    @FXML
    public void cancelButtonOnAction(ActionEvent e) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();


    }

    public void onLoad() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("login.fxml"));
        Parent myPagesParent = fxmlLoader.load();
        Scene myPagesScene = new Scene(myPagesParent);
        Stage currentStage = (Stage) loginbutton.getScene().getWindow();
        currentStage.setScene(myPagesScene);
    }

    public void searchbuttonAction() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("searchnotlogin.fxml"));
        Parent myPagesParent = fxmlLoader.load();
        Scene myPagesScene = new Scene(myPagesParent);
        Stage currentStage = (Stage) searchButton.getScene().getWindow();
        currentStage.setScene(myPagesScene);

    }

    public void onadminLogin() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("adminLogin.fxml"));
        Parent myPagesParent = fxmlLoader.load();
        Scene myPagesScene = new Scene(myPagesParent);
        Stage currentStage = (Stage) adminButton.getScene().getWindow();
        currentStage.setScene(myPagesScene);
    }
    public void onReturnBook1() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("returnBook.fxml"));
        Parent myPagesParent = fxmlLoader.load();
        Scene myPagesScene = new Scene(myPagesParent);
        Stage currentStage = (Stage) ReturnBook1.getScene().getWindow();
        currentStage.setScene(myPagesScene);
    }
    public void onReturnDvd1() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("returnDVD.fxml"));
        Parent myPagesParent = fxmlLoader.load();
        Scene myPagesScene = new Scene(myPagesParent);
        Stage currentStage = (Stage) returnDvd1.getScene().getWindow();
        currentStage.setScene(myPagesScene);
    }
}