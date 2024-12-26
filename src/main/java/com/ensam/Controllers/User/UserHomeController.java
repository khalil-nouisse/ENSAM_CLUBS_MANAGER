package com.ensam.Controllers.User;

import com.ensam.Controllers.AppUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class UserHomeController {
    @FXML
    private void goToAbout(ActionEvent event) {
        AppUtils.navigateTo(event, "/Fxml/About.fxml", "About Page");
    }

    //Sign out button
    public void logout(ActionEvent event){
        AppUtils.logout(event);
    }
}
