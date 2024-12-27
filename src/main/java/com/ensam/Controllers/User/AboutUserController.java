package com.ensam.Controllers.User;

import com.ensam.Controllers.AppUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class AboutUserController {

    @FXML
    private Button about_btnUser;

    @FXML
    private Button home_btnUser;

    @FXML
    private AnchorPane main_form;

    @FXML
    private AnchorPane manager_form;

    @FXML
    private Button signout_btnAbt;


    public void logoutUser(ActionEvent event){
        AppUtils.logout(event);
    }

    @FXML
    private void goToHomeUser(ActionEvent event) {
        AppUtils.navigateTo(event, "/Fxml/User/HomeUser.fxml", "Home Page");
    }

    public void initialize() {

        about_btnUser.setDisable(true);

    }
}
