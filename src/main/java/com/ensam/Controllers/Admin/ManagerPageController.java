package com.ensam.Controllers.Admin;

import com.ensam.Backend.database.CLubDb;
import com.ensam.Backend.model.Club;
import com.ensam.Backend.model.data;
import com.ensam.Controllers.AppUtils;
import com.ensam.Controllers.HomeController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.awt.*;
import java.awt.desktop.OpenFilesEvent;
import java.io.File;
import java.net.URL;
import java.util.*;
import java.util.List;
import static com.ensam.Backend.database.CLubDb.clubExist;

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
    private ComboBox<String> manager_add_clubCategory;

    @FXML
    private TextField manager_add_clubDescription;

    @FXML
    private TextField manager_add_clubName;

    @FXML
    private ComboBox<?> manager_add_clubState;

    @FXML
    private ImageView manager_add_image;

    @FXML
    private TextField manager_searchBar;

    Alert alert;
    CLubDb clubDb = new CLubDb();
    private  Image image;
    HomeController homeController ;
    // category list in the manager page !
    String []categoryList = {"Technical" , "Art & Culture" , "Entrepreneurship" , "Creation"};
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
                || manager_add_clubDescription.getText().isEmpty()
                || data.path == null){
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please fill all club informations");
            alert.showAndWait();
        }
        else{
            try {
                //check if club already exists in the database
                if(clubExist(manager_add_clubName.getText())){
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText("Club exist already");
                    alert.setContentText("this CLub already exist !");
                    alert.showAndWait();
                }
                else {

                    //add club
                    clubDb.saveClubToDatabase(manager_add_clubName.getText()
                            , (String) manager_add_clubCategory.getSelectionModel().getSelectedItem()
                            , (String) manager_add_clubState.getSelectionModel().getSelectedItem()
                            , manager_add_clubDescription.getText()
                            , data.path);

                    //homeController.put_logo(data.path);
                    //homeController.put_logo(data.path);

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
                    manager_add_image.setImage(null);
                }
            }
            catch (Exception e){
                System.out.println("Error adding club to database!");
                e.printStackTrace();
            }
        }
    }
    //manager impot image button
    public void managerImportBtn(ActionEvent e){
        FileChooser openFile = new FileChooser();
        openFile.getExtensionFilters().add(new FileChooser.ExtensionFilter("Open Image File ","*png","*jpg"));
        File file  = openFile.showOpenDialog(main_form.getScene().getWindow());
        if(file != null){
            data.path = file.getAbsolutePath();
            image = new Image(file.toURI().toString(), 120, 127, false, true);

            manager_add_image.setImage(image);
        }
    }
    //delete club button
    public void managerDeleteClub(ActionEvent e){

        if(manager_add_clubName.getText().isEmpty()){      //in case the name of the club to delete not available
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Enter the club name !");
            alert.showAndWait();
        }
        else {                                            //delete og the club
            //confirmation message !
            alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText("Delete Club Confirmation");
            alert.setContentText("Are you sure you want to delete the club?");

            //control the choice
            Optional<ButtonType> option = alert.showAndWait() ;
            if (option.get().equals(ButtonType.OK)) {         //confirm exit
                clubDb.deleteClubFromDb(manager_add_clubName.getText());
                //intitialise the information area
                manager_add_clubName.setText("");
                manager_add_clubCategory.getSelectionModel().clearSelection();
                manager_add_clubState.getSelectionModel().clearSelection();
                manager_add_clubDescription.setText("");
                // Reload the clubs table data
                managerShowData();
            }
        }
    }

    public void managerClearDataBtn(){
        manager_add_clubName.setText("");
        manager_add_clubCategory.getSelectionModel().clearSelection();
        manager_add_clubState.getSelectionModel().clearSelection();
        manager_add_clubDescription.setText("");
        manager_add_image.setImage(null);
    }
    public void managerSearchBar(ActionEvent e){

        ObservableList<Club> clublist = FXCollections.observableArrayList();
        CLubDb db = new CLubDb();
        clublist = db.selectClubsFromDb();
        // Create a FilteredList for dynamic filtering
        FilteredList<Club> filteredClubs = new FilteredList<>(clublist, p -> true);
        // Bind the FilteredList to the TableView
        manager_club_table.setItems(filteredClubs);
        // Add a listener to the search bar
        manager_searchBar.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredClubs.setPredicate(club -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true; // Show all clubs if the search bar is empty
                }

                String lowerCaseFilter = newValue.toLowerCase();
                return club.getClubName().toLowerCase().contains(lowerCaseFilter);
            });
        });
    }
    //Sign out button
    public void logout(ActionEvent event){
        AppUtils.logout(event);
    }


    @FXML
    private void goToHome(ActionEvent event) {
        AppUtils.navigateTo(event, "/Fxml/Home.fxml", "Home Page");
    }


    @FXML
    private void goToAbout(ActionEvent event) {
        AppUtils.navigateTo(event, "/Fxml/About.fxml", "About Page");
    }

    //initialize hthe manager home page !
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        manager_btn.setDisable(true);   //this one to make the manager button unclickable since it's already opened
        setManager_club_category();
        setManager_club_state();
        managerShowData();

    }
}
