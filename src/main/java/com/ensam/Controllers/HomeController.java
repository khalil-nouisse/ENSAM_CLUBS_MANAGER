package com.ensam.Controllers;

import com.ensam.Backend.database.CLubDb;
import com.ensam.Backend.database.ClubMemberDb;
import com.ensam.Backend.model.Club;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class HomeController implements Initializable {
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
    private Button manager_btn;

    @FXML
    private AnchorPane manager_form;

    @FXML
    private GridPane menu_gridPane;

    @FXML
    private ScrollPane menu_scrollPane;

    @FXML
    private Button signout_btn;

    @FXML
    private Button UserAboutBtn;

    CLubDb clubdb  = new CLubDb();
    Club club ;
    private ObservableList<Club> cardListData = clubdb.selectClubsFromDb();
    ClubMemberDb clubmember = new ClubMemberDb();
    Alert alert;

    public void menuDisplayCard(){
        cardListData.clear();
        cardListData.addAll(clubdb.selectClubsFromDb());

        int row = 0;
        int column = 0 ;

        menu_gridPane.getChildren().clear();
        menu_gridPane.getRowConstraints().clear();
        menu_gridPane.getColumnConstraints().clear();
        for(int q= 0 ; q <cardListData.size() ;q++){
            try {
                FXMLLoader load = new FXMLLoader();
                load.setLocation(getClass().getResource("/Fxml/cardClub.fxml"));
                AnchorPane pane = load.load();

                CardClubController cardC = load.getController();
                cardC.setData(cardListData.get(q));
                cardC.setHomeController(this); // Pass reference to HomeController

                if(column == 2){
                    column = 0 ;
                    row+=1;
                }
                menu_gridPane.add(pane , column++ ,row );
                GridPane.setMargin(pane, new Insets(5));
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public void showClubDetails(Club club) {
        // Update the right panel with club details
        home_club_infoName.setText(club.getClubName());
        home_club_infoCategory.setText(club.getClubCategory());
        home_club_infoMembers.setText(String.valueOf(club.getClubMembers()));
        home_club_infoState.setText(club.getClubState());
        home_club_infoDescription.setText(club.getClubDescription());
        home_club_infoDescription.setWrapText(true);
        home_club_infoLogo.setImage(new Image(club.getClubImage()));
    }


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

    @FXML
    private void goToAboutUser(ActionEvent event) {
        AppUtils.navigateTo(event, "/Fxml/User/UserAbout.fxml", "About Page");
    }


    public void JoinClubButton(ActionEvent event){

        Boolean val;
        val = clubmember.insertClubMember(AppUtils.Session.getLoggedInUsername(),home_club_infoName.getText());
        if(val){        //joined club succesfully
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Message");
            alert.setHeaderText(null);
            alert.setContentText("Succesfully Joined the club !");
            alert.showAndWait();

            //add one member to members

        }
        else{   //couldn't join the club
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR Message");
            alert.setHeaderText(null);
            alert.setContentText("Couldn't join the Club !");
            alert.showAndWait();
        }
    }

    private int col = 0;
    private int row = 0;
    private final int columns = 3;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        menuDisplayCard();
        home_btn.setDisable(true);
    }

}
