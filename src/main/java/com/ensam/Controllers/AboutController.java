package com.ensam.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class AboutController {
    @FXML
    private Button about_btn;

    @FXML
    private Button home_btn;

    @FXML
    private AnchorPane main_form;

    @FXML
    private Button manager_btn;

    @FXML
    private AnchorPane manager_form;

    @FXML
    private Button signout_btn;

    public void logout(ActionEvent event){
        AppUtils.logout(event);
    }

    /*
    @FXML
    private void goToHome(ActionEvent event) {
        AppUtils.navigateTo(event, "/Fxml/Home.fxml", "Home Page");
    }
    */

    @FXML
    private void goToManager(ActionEvent event) {
        AppUtils.navigateTo(event, "/Fxml/Admin/managerPage.fxml", "Manager Page");
    }

    public void initialize() {

        about_btn.setDisable(true);

    }

}

