package com.ensam.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.util.Optional;

public class AppUtils {
    // this class contains all the commun functions between the 3 pages

    public static void logout(ActionEvent event){
        Alert alert ;
        try{
            //logout confirmation
            alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("CONFIRMATION");
            alert.setHeaderText("Sign Out Confirmation");
            alert.setContentText("Are you sure you want to sign out?");

            //controle the choice of the user !
            Optional<ButtonType> option = alert.showAndWait() ;
            if(option.get().equals(ButtonType.OK)){         //after logout confirmation go back to login page!

                // Load the MainInterface.fxml
                FXMLLoader loader = new FXMLLoader(AppUtils.class.getResource("/Fxml/Login.fxml"));
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

    public static void navigateTo(ActionEvent event, String fxmlPath, String title) {
        try {
            // Load the FXML file
            FXMLLoader loader = new FXMLLoader(AppUtils.class.getResource(fxmlPath));
            Parent root = loader.load();

            // Get the current stage from the event source
            Stage currentStage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();

            // Set the new scene
            Scene scene = new Scene(root);
            currentStage.setTitle(title);
            currentStage.setScene(scene);

            // Show the updated stage
            currentStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public class Session {
        private static String loggedInUsername;

        public static void setLoggedInUsername(String username) {
            loggedInUsername = username;
        }

        public static String getLoggedInUsername() {
            return loggedInUsername;
        }
    }

}
