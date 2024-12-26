package com.ensam.Backend.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ClubMemberDb {

    // Database connection details
    String url = "jdbc:mysql://localhost:3306/ensamclubmanager";
    String username = "root";
    String password = "";

    // Method to insert a club-member relationship
    public boolean insertClubMember(String membername, String clubname) {
        String insertQuery = "INSERT INTO club_members (club_name, member_name, join_date) VALUES (?, ?, CURRENT_DATE)";

        try(Connection connection = DriverManager.getConnection(url , username ,password );
             PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {

            // Set the parameters for the query
            preparedStatement.setString(1, clubname);
            preparedStatement.setString(2, membername);

            // Execute the update
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0; // Return true if insertion was successful

        } catch (SQLException e) {
            System.err.println("Error inserting club-member relationship: " + e.getMessage());
            return false;
        }
    }

}