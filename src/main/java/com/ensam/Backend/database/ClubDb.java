/*package com.ensam.Backend.database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import com.ensam.Backend.model.Club;
import java.util.ArrayList;
import java.sql.ResultSet;
import com.ensam.Backend.manager.ClubManager;

public class ClubDb {
public static void saveClubToDatabase(String clubname, String clubcategory) {
Club club = new Club(clubname,clubcategory);        //constructor of the new club
ClubManager cm = new ClubManager() ;

String url = "jdbc:mysql://localhost:3306/ensamclubs";
String username = "root";
String password = "";

cm.addClub(club);

long ID = club.getClubID();
int membersNumber = club.getClubMembers();
boolean clubState = club.isClubState();
long clubScore = club.getClubScore() ;

String query = "insert into clubs_management(ID,name,category,members,state,score) VALUES (?, ?, ?, ?, ?, ?)";

try (Connection connection = DriverManager.getConnection(url, username, password);
     PreparedStatement preparedStatement = connection.prepareStatement(query)) {

    preparedStatement.setLong(1, ID   );
    preparedStatement.setString(2, clubname);
    preparedStatement.setString(3, clubcategory);
    preparedStatement.setInt(4, membersNumber);
    preparedStatement.setBoolean(5, clubState);
    preparedStatement.setLong(6, clubScore);

    int rowsInserted = preparedStatement.executeUpdate();
    if (rowsInserted > 0) {
        System.out.println("CLUBS data saved successfully!");
    }

} catch (Exception e) {
    System.out.println("Error: " + e.getMessage());
}
}
public static ArrayList<Club> loadClubsFromDatabase() {
ArrayList<Club> clubs = new ArrayList<>();
String query = "SELECT * FROM ensam_clubs";
String url = "jdbc:mysql://localhost:3306/ensamclubs";
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
    }

}
            */