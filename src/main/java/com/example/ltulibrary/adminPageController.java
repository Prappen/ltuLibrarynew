package com.example.ltulibrary;

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

public class adminPageController  implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


    }

    @FXML
    private Button adminReturnButton;
    @FXML
    private Button bookButton;
    @FXML
    private Button DvdButton;
    @FXML
    private Button handleLoanButton;



    public void OnAdminReturn() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("startPage.fxml"));
        Parent myPagesParent = fxmlLoader.load();
        Scene myPagesScene = new Scene(myPagesParent);
        Stage currentStage = (Stage) adminReturnButton.getScene().getWindow();
        currentStage.setScene(myPagesScene);


    }
    public void onBook() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("adminBook.fxml"));
        Parent myPagesParent = fxmlLoader.load();
        Scene myPagesScene = new Scene(myPagesParent);
        Stage currentStage = (Stage) bookButton.getScene().getWindow();
        currentStage.setScene(myPagesScene);


    }
    public void onDvd() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("adminDVD.fxml"));
        Parent myPagesParent = fxmlLoader.load();
        Scene myPagesScene = new Scene(myPagesParent);
        Stage currentStage = (Stage) DvdButton.getScene().getWindow();
        currentStage.setScene(myPagesScene);


    }
    public void onHandleLoan() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("hantera.fxml"));
        Parent myPagesParent = fxmlLoader.load();
        Scene myPagesScene = new Scene(myPagesParent);
        Stage currentStage = (Stage) handleLoanButton.getScene().getWindow();
        currentStage.setScene(myPagesScene);


    }
}



