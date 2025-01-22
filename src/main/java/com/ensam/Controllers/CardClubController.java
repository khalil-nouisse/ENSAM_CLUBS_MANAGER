package com.ensam.Controllers;

import com.ensam.Backend.model.Club;
import com.ensam.Backend.model.data;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class CardClubController implements Initializable {
    @FXML
    private Label card_clubName;

    @FXML
    private ImageView card_club_logo;

    private Club club ;
    private Image image ;

    private HomeController homeController;

    public void setData(Club club){
        this.club = club;
        card_clubName.setText(club.getClubName());
        String path = "File:" + club.getClubImage();
        image = new Image(path, 186, 179, false, true);
        card_club_logo.setImage(image);
    }

    // Method to set the reference to the HomeController
    public void setHomeController(HomeController homeController) {
        this.homeController = homeController;
    }

    // Method to handle card click
    @FXML
    private void onCardClick() {
        if (homeController != null && club != null) {
            homeController.showClubDetails(club); // Notify the HomeController
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
