package com.ensam.Controllers.User;

import com.ensam.Controllers.AppUtils;
import com.ensam.Controllers.HomeController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

public class UserHomeController {

    @FXML
    private Button about_btn;

    @FXML
    private Button home_btn;

    @FXML
    private Label home_club_infoCategory;

    @FXML
    private Label home_club_infoDescription;

    @FXML
    private ImageView home_club_infoLogo;

    @FXML
    private Label home_club_infoMembers;

    @FXML
    private Label home_club_infoName;

    @FXML
    private Label home_club_infoState;

    @FXML
    private AnchorPane main_form;

    @FXML
    private AnchorPane manager_form;

    @FXML
    private GridPane menu_gridPane;

    @FXML
    private ScrollPane menu_scrollPane;

    @FXML
    private AnchorPane right_panel;

    @FXML
    private Button signout_btn;


    @FXML
    private void goToAbout(ActionEvent event) {
        AppUtils.navigateTo(event, "/Fxml/User/UserAbout.fxml", "About Page");
    }

    //Sign out button
    public void logout(ActionEvent event){
        AppUtils.logout(event);
    }


}
