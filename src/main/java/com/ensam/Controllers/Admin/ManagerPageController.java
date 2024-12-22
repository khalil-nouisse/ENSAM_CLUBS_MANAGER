package com.ensam.Controllers.Admin;

import com.ensam.Backend.database.CLubDb;
import com.ensam.Backend.model.Club;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.*;


public class ManagerPageController implements Initializable {


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
    private TableColumn<Club,String> manager_club_category;

    @FXML
    private TableColumn<Club,String> manager_club_description;

    @FXML
    private TableColumn<Club,String> manager_club_id;

    @FXML
    private TableColumn<Club,String> manager_club_name;

    @FXML
    private TableColumn<Club,String> manager_club_state;

    @FXML
    private TableView<Club> manager_club_table;

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

    @FXML
    private ImageView manager_add_image;

    Alert alert;
    CLubDb clubDb = new CLubDb();

    // category list in the manager page !
    private String []categoryList = {"Technical" , "Art & Culture" , "Entrepreneurship" , "Creation"};
    public void setManager_club_category(){
        List<String> categoryL = new ArrayList<>() ;
        Collections.addAll(categoryL ,categoryList);
        ObservableList category = FXCollections.observableArrayList(categoryL);
        manager_add_clubCategory.setItems(category);

    }

    //state list in the manager page
    private String []stateListe = {"Active" ,"Inactive "} ;
    public void setManager_club_state(){
        List <String> stateL = new ArrayList<>() ;
        Collections.addAll(stateL , stateListe);
        ObservableList state = FXCollections.observableArrayList(stateL);
        manager_add_clubState.setItems(state);
    }




    //Show off the manager page table
    private ObservableList<Club> managerListData ;
    public void managerShowData(){

        managerListData = clubDb.selectClubsFromDb();

        manager_club_id.setCellValueFactory(new PropertyValueFactory<>("clubID"));
        manager_club_name.setCellValueFactory(new PropertyValueFactory<>("clubName"));
        manager_club_category.setCellValueFactory(new PropertyValueFactory<>("clubCategory"));
        manager_club_state.setCellValueFactory(new PropertyValueFactory<>("clubState"));
        manager_club_description.setCellValueFactory(new PropertyValueFactory<>("clubDescription"));


        manager_club_table.setItems(managerListData);
    }


    //add club button
    public void managerAddClub(ActionEvent event){

        if(manager_add_clubName.getText().isEmpty()
                || manager_add_clubCategory.getSelectionModel().isEmpty()
                || manager_add_clubState.getSelectionModel().isEmpty()
                || manager_add_clubDescription.getText().isEmpty()){
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please fill all club informations");
            alert.showAndWait();
        }
        else{
            try {

               clubDb.saveClubToDatabase(manager_add_clubName.getText()
                        ,(String) manager_add_clubCategory.getSelectionModel().getSelectedItem()
                        ,(String)manager_add_clubState.getSelectionModel().getSelectedItem()
                        ,manager_add_clubDescription.getText()  );

                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Message");
                alert.setHeaderText(null);
                alert.setContentText("Succesfully added club!");
                alert.showAndWait();

                // Reload the clubs table data
                managerShowData();

                //initialize the data boxes
                manager_add_clubName.setText("");
                manager_add_clubCategory.getSelectionModel().clearSelection();
                manager_add_clubState.getSelectionModel().clearSelection();
                manager_add_clubDescription.setText("");

            }
            catch (Exception e){
                System.out.println("Error adding club to database!");
                e.printStackTrace();
            }
        }
    }

    //delete club button
    public void managerDeleteClub(ActionEvent e){

        //confirmation message !
        alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Delete Club Confirmation");
        alert.setContentText("Are you sure you want to delete the club?");

        //control the choice
        Optional<ButtonType> option = alert.showAndWait() ;
        if(option.get().equals(ButtonType.OK)){
            clubDb.deleteClubFromDb(manager_add_clubName.getText());
            //intitialise the information area
            manager_add_clubName.setText("");
            manager_add_clubCategory.getSelectionModel().clearSelection();
            manager_add_clubState.getSelectionModel().clearSelection();
            manager_add_clubDescription.setText("");
            // Reload the clubs table data
            managerShowData();

        }
        else{

        }

    }

    public void managerClearDataBtn(){
        manager_add_clubName.setText("");
        manager_add_clubCategory.getSelectionModel().clearSelection();
        manager_add_clubState.getSelectionModel().clearSelection();
        manager_add_clubDescription.setText("");
    }

    //Sign out button
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


    //initialize hthe manager home page !
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        manager_btn.setDisable(true);   //this one to make the manager button unclickable since it's already opened
        setManager_club_category();
        setManager_club_state();
        managerShowData();

        //clubDb.loadClubsFromDatabase(clubList);
        //loadClubs();
    }
}
