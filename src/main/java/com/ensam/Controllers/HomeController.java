package com.ensam.Controllers;

import com.ensam.Backend.model.data;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

public class HomeController {
    @FXML
    private Button about_btn;

    @FXML
    private Button home_btn;

    @FXML
    private GridPane logoGrid;

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

    @FXML
    private void goToManager(ActionEvent event) {
        AppUtils.navigateTo(event, "/Fxml/Admin/managerPage.fxml", "Manager Page");
    }

    @FXML
    private void goToAbout(ActionEvent event) {
        AppUtils.navigateTo(event, "/Fxml/About.fxml", "About Page");
    }

    private int col = 0;
    private int row = 0;
    private final int columns = 3;

    public void put_logo(String image){

        try {
            ImageView imageView = new ImageView(new Image(image));
            imageView.setFitWidth(200);
            imageView.setFitHeight(200);
            imageView.setPreserveRatio(true);
            GridPane.setMargin(imageView, new javafx.geometry.Insets(5));
            logoGrid.add(imageView, col, row);
            col++;
            if (col >= columns) {
                col = 0;
                row++;
            }

        }catch (Exception e){
            System.out.println("Path in existe !!!!");
        }

    }

}
