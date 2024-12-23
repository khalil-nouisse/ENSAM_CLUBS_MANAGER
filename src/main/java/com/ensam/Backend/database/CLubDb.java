package com.ensam.Backend.database;
import java.net.URL;
import java.sql.*;

import com.ensam.Backend.model.Club;
import java.util.ArrayList;
import java.util.List;

import com.ensam.Backend.manager.ClubManager;
import com.ensam.Backend.model.data;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;


public class CLubDb {


    public static boolean clubExist(String clubname){

        String url = "jdbc:mysql://localhost:3306/ensamclubmanager";
        String username = "root";
        String password = "";

        String checkClubName = "SELECT clubName FROM clubs WHERE clubName = '"+ clubname + "'";
        try(Connection connect = DriverManager.getConnection(url , username ,password );
            PreparedStatement prepare = connect.prepareStatement(checkClubName)){
            ResultSet result = prepare.executeQuery();
            if(result.next()){
                //this club already exists !
                return true ;
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return false ;  //the club doesnt exist
    }

    //insert club to database table clubs
    public  static void saveClubToDatabase(String clubname, String clubcategory ,String clubState ,String clubdescription, String clubimage) {

        String url = "jdbc:mysql://localhost:3306/ensamclubmanager";
        String username = "root";
        String password = "";

        Club club = new Club(clubname,clubcategory,clubState, clubdescription , clubimage);        //constructor of the new club
        ClubManager cm = new ClubManager() ;

        cm.addClub(club);

        long membersNum = club.getClubMembers();

        String query = "insert into clubs(clubName,clubCategory,clubState,clubDescription,membersNumber,clubImage) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, clubname);
            preparedStatement.setString(2, clubcategory);
            preparedStatement.setString(3, clubState);
            preparedStatement.setString(4, clubdescription);
            preparedStatement.setLong(5, membersNum);
            //preparedStatement.setString(6, clubimage);
            String path = clubimage;
            path = path.replace("\\", "\\\\");

            preparedStatement.setString(6, path);

            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("CLUBS data saved successfully!");
            }

        } catch (Exception e) {
            System.out.println("Error inserting clubs to database! : " + e.getMessage());
        }
    }

    private PreparedStatement prepare;
    private Statement statement ;
    private ResultSet result;

    // load the clubs from the databsae to an Observable list , to be used to show off the data in manager table
    public ObservableList<Club> selectClubsFromDb(){

        String url = "jdbc:mysql://localhost:3306/ensamclubmanager";
        String username = "root";
        String password = "";

        ObservableList<Club> listData = FXCollections.observableArrayList();
        String sql = "SELECT * FROM clubs";


        try (Connection connect = DriverManager.getConnection(url, username, password);){
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            Club club;
            while (result.next()) {
                club = new Club(result.getLong("clubID")
                        , result.getString("clubName")
                        , result.getString("clubCategory")
                        , result.getString("clubState")
                        , result.getString("clubDescription"));
                listData.add(club);
            }

        }
        catch (Exception e) {
                e.printStackTrace();
        }
            return listData;

    }

    //delete existing club from database table clubs
    public Boolean deleteClubFromDb(String nameClub){
        String url = "jdbc:mysql://localhost:3306/ensamclubmanager";
        String username = "root";
        String password = "";

        String querry = "SELECT clubName FROM clubs WHERE clubName = '"+ nameClub + "'" ;
        String deleteQuerry = "DELETE FROM clubs WHERE clubName = '"+ nameClub + "'" ;


        try(Connection connect = DriverManager.getConnection(url,username,password)){
            prepare = connect.prepareStatement(querry);
            result = prepare.executeQuery();
            if (result.next()){
                prepare = connect.prepareStatement(deleteQuerry);
                prepare.executeUpdate();
                return true ;
            }


        }
        catch (Exception e){
            e.printStackTrace();
        }
        return false ;
    }


}
