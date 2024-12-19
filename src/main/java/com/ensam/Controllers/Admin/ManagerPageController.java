package com.ensam.Controllers.Admin;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.*;


public class ManagerPageController {


    @FXML
    private Button about_btn;

    @FXML
    private Button home_btn;

    @FXML
    private AnchorPane main_form;

    @FXML
    private Button manager_addBtn;

    @FXML
    private Button manager_btn;

    @FXML
    private TableColumn<?, ?> manager_club_category;

    @FXML
    private TableColumn<?, ?> manager_club_description;

    @FXML
    private TableColumn<?, ?> manager_club_id;

    @FXML
    private TableColumn<?, ?> manager_club_name;

    @FXML
    private TableColumn<?, ?> manager_club_state;

    @FXML
    private TableView<?> manager_club_table;

    @FXML
    private Button manager_delBtn;

    @FXML
    private AnchorPane manager_form;

    @FXML
    private Button manager_importBtn;

    @FXML
    private Button signout_btn;

    @FXML
    private ComboBox<?> manager_add_clubCategory;

    @FXML
    private TextField manager_add_clubDescription;

    @FXML
    private TextField manager_add_clubName;

    @FXML
    private ComboBox<?> manager_add_clubState;

    Alert alert;
    private String []categoryList = {"Technical" , "Art & Culture" , "Entrepreneurship" , "Creation"};
    public void inventoryTypeList(){
        List<String> categoryL = new ArrayList<>() ;
        /*for(String data : categoryList){
            categoryL.add(data);*/

        Collections.addAll(categoryL ,categoryList);
        ObservableList category = FXCollections.observableArrayList(categoryL);
        manager_add_clubCategory.setItems(category);

    }

    public void logout(ActionEvent event){

        try{

            //logout confirmation
            alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("CONFIRMATION");
            alert.setHeaderText("Sign Out Confirmation");
            alert.setContentText("Are you sure you want to sign out?");

            //controle the choice of the user !

            Optional<ButtonType> option = alert.showAndWait() ;

            if(option.get().equals(ButtonType.OK)){



                //after logout confirmation go back to login page!

                // Load the MainInterface.fxml
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Login.fxml"));
                Parent mainInterfaceRoot = loader.load();

                // Get the current stage from the event source
                Stage currentStage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();

                // Set the new scene
                Scene mainScene = new Scene(mainInterfaceRoot);
                currentStage.setTitle("CLUBS MANAGER");
                currentStage.setScene(mainScene);

                // Resize the stage to fit the new scene
                currentStage.sizeToScene();
                currentStage.setMinWidth(600);
                currentStage.setMinHeight(400);
                currentStage.setMaxWidth(600);
                currentStage.setMaxHeight(400);

                // Show the updated stage
                currentStage.show();
            }

            //User clicked No.wants to stay logged in


        }catch (Exception e){
            e.printStackTrace();
        }
    }
    /*public void initialize(URL location , ResourceBundle resources){
        //inventoryTypeList();
    }*/


}
