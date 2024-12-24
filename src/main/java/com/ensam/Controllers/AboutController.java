package com.ensam.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class AboutController {
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

}

