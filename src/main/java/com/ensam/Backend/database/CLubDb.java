package com.ensam.Backend.database;
import java.net.URL;
import java.sql.*;

import com.ensam.Backend.model.Club;
import java.util.ArrayList;
import java.util.List;

import com.ensam.Backend.manager.ClubManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;


public class CLubDb {



    public  static void saveClubToDatabase(String clubname, String clubcategory ,String clubState ,String clubdescription) {
        String url = "jdbc:mysql://localhost:3306/ensamclubmanager";
        String username = "root";
        String password = "";
        Club club = new Club(clubname,clubcategory,clubState, clubdescription);        //constructor of the new club
        ClubManager cm = new ClubManager() ;



        cm.addClub(club);

        long membersNum = club.getClubMembers();

        String query = "insert into clubs(clubName,clubCategory,clubState,clubDescription,membersNumber) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            //preparedStatement.setLong(1, ID   );
            preparedStatement.setString(1, clubname);
            preparedStatement.setString(2, clubcategory);
            preparedStatement.setString(3, clubState);
            preparedStatement.setString(4, clubdescription);
            preparedStatement.setLong(5, membersNum);

            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("CLUBS data saved successfully!");
            }

        } catch (Exception e) {
            System.out.println("Error inserting clubs to database! : " + e.getMessage());
        }
    }
    /*public static ArrayList<Club> loadClubsFromDatabase() {
        ArrayList<Club> clubs = new ArrayList<>();
        String query = "SELECT * FROM clubs";
        String url = "jdbc:mysql://localhost:3306/ensamclubmanager";
        String username = "root";
        String password = "";
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Club club = new Club();
                club.setClubName(resultSet.getString("clubname"));
                club.setClubCategory(resultSet.getString("clubcategory"));
                // Set other properties if needed
                clubs.add(club);
            }

        } catch (Exception e) {
            System.err.println("Error loading clubs from database: " + e.getMessage());
            e.printStackTrace();
        }

        return clubs;
        }*/

    public ObservableList<Club> loadClubsFromDatabase() {
        String url = "jdbc:mysql://localhost:3306/ensamclubmanager";
        String username = "root";
        String password = ""; //


        ObservableList<Club> clubList = FXCollections.observableArrayList();

        String query = "SELECT * FROM clubs";

        try (Connection conn = DriverManager.getConnection(url, username, password);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                int id = rs.getInt("clubID");
                String name = rs.getString("clubName");
                String category = rs.getString("clubCategory");
                String state = rs.getString("clubState");
                String description = rs.getString("clubDescription");

                // Add the club to the list
                clubList.add(new Club(id, name, category, state, description));
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        return clubList;
    }



}
