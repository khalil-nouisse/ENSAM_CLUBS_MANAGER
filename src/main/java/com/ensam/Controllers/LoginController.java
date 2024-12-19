package com.ensam.Controllers;
import com.ensam.Backend.database.MemberDb;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javafx.event.ActionEvent;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import com.ensam.Controllers.Admin.ManagerPageController;

public class LoginController {
    @FXML
    private Button si_loginBtn;

    @FXML
    private AnchorPane si_loginForm;

    @FXML
    private PasswordField si_password;

    @FXML
    private TextField si_username;

    @FXML
    private Button side_createBtn;

    @FXML
    private AnchorPane side_forme;

    @FXML
    private TextField su_StudentID;

    @FXML
    private ComboBox<?> su_Year;

    @FXML
    private PasswordField su_password;

    @FXML
    private Button su_signupBtn;

    @FXML
    private AnchorPane su_signupForm;

    @FXML
    private TextField su_username;

    @FXML
    private Button side_alreadyHave;

    ManagerPageController  managerpage = new ManagerPageController();

    // ----------------- connection interface backend/database ------------------
    MemberDb memberDb = new MemberDb();
    Alert alert ;

    //Login BLOCK
    @FXML
    public void loginBtn(ActionEvent event){
        if(si_username.getText().isEmpty() || si_password.getText().isEmpty()){         //make sure fill the blanks
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please fill all blank fields");
            alert.showAndWait();
        }
        else{
            try{
                if(memberDb.LoginVerification(si_username.getText() , si_password.getText())){         //succesfull login
                     alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Succesfully Login !");
                    alert.showAndWait();



                    // Load the MainInterface.fxml

                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Admin/managerPage.fxml"));
                    Parent root = loader.load();       //error here

                    //Get the current stage from the event source
                    Stage currentStage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();

                    // Set the new scene
                    Scene mainScene = new Scene(root);
                    currentStage.setScene(mainScene);

                    // Resize the stage to fit the new scene
                    currentStage.sizeToScene();
                    currentStage.setMinWidth(1018);
                    currentStage.setMinHeight(690);
                    currentStage.setMaxWidth(1018);
                    currentStage.setMaxHeight(690);

                    // Show the updated stage
                    currentStage.show();

                }
                else{           //login failed
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("ERROR Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Incorrect Username/Password !");
                    alert.showAndWait();
                }
            }catch (Exception e){
                e.printStackTrace();
                System.out.println("Login ERROR");
            }

        }
    }

    //registration BLOCK
    public void regBtn(){           //registration button

            // make sure all the fileds are filled for registration !
            if(su_username.getText().isEmpty() || su_password.getText().isEmpty()
                    ||su_StudentID.getText().isEmpty()
                    || su_Year.getSelectionModel().getSelectedItem() == null ){

                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            }
            else{       // database connection for member registration !
                try {
                    int value = memberDb.checkValues(su_username.getText() , su_password.getText());
                    if(value == 1 || value == 3){         //username is already taken !
                        alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error Message");
                        alert.setHeaderText(null);
                        alert.setContentText("the username ' "+su_username.getText() + " ' is already taken !");
                        alert.showAndWait();
                    }
                    else if(value == 2){    // password is shor (less than 3 characters
                        alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error Message");
                        alert.setHeaderText(null);
                        alert.setContentText("Invalid Password ! at least 4 characters is needed.");
                        alert.showAndWait();
                    }
                    else {  // register the new member
                        memberDb.saveMemberToDatabase(su_username.getText(), su_password.getText(), su_StudentID.getText(), (int) su_Year.getSelectionModel().getSelectedItem());
                        alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Information Message");
                        alert.setHeaderText(null);
                        alert.setContentText("Succesfully registered Account!");
                        alert.showAndWait();
                        //initialize the data boxes
                        su_username.setText("");
                        su_password.setText("");
                        su_StudentID.setText("");
                        su_Year.getSelectionModel().clearSelection();

                        //slide to the login once finished the registration succesfully
                        TranslateTransition slider = new TranslateTransition();

                        slider.setNode(side_forme);
                        slider.setToX(0);
                        slider.setDuration(Duration.seconds(.5));
                        slider.setOnFinished(e -> {
                            side_alreadyHave.setVisible(false);
                            side_createBtn.setVisible(true);
                        });
                        slider.play();
                    }


                }catch (Exception e){
                    e.printStackTrace();
                }

            }

        }
        //this block of code shows the list of years in signup page
        private Integer years[] = {1 , 2 ,3, 4, 5} ;
        public void regYearList(){
            List<Integer> listY = new ArrayList<>();
            for(Integer data: years){
                listY.add(data);
            }

            ObservableList listYear = FXCollections.observableArrayList(listY);
            su_Year.setItems(listYear);

        }


        // Sliding effect on the login menue
        public void switchForm(ActionEvent event){
        TranslateTransition slider = new TranslateTransition();
        if(event.getSource() == side_createBtn){
            slider.setNode(side_forme);
            slider.setToX(300);   //move form to the left
            slider.setDuration(Duration.seconds(.5));
            slider.setOnFinished(e -> {
                side_alreadyHave.setVisible(true);
                side_createBtn.setVisible(false);

                regYearList();
            });

            slider.play();
        }
        else if (event.getSource() == side_alreadyHave) {
            slider.setNode(side_forme);
            slider.setToX(0);
            slider.setDuration(Duration.seconds(.5));
            slider.setOnFinished( e -> {
                side_alreadyHave.setVisible(false);
                side_createBtn.setVisible(true);
            });

            slider.play();
        }
    }


}

