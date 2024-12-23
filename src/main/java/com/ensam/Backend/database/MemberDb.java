package com.ensam.Backend.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.ensam.Backend.manager.MembersManager;
import com.ensam.Backend.model.HR.ExecutiveOffice;
import com.ensam.Backend.model.HR.Member;
import javafx.scene.control.Alert;


public class MemberDb {

    String url = "jdbc:mysql://localhost:3306/ensamclubmanager";
    String username = "root";
    String pw = "";

        public  void saveMemberToDatabase(String membername, String password , String studentID , int year) {

            Member member = new Member(studentID, membername, password, year);        //constructor of the new member
            MembersManager mm = new MembersManager();

            mm.addMember(member);           // add member to member's list

            String query = "insert into members(ID,studentName,password,year,role) VALUES (?, ?, ?, ?, ?)";

            try (Connection connection = DriverManager.getConnection(url, username, pw);
                 PreparedStatement preparedStatement = connection.prepareStatement(query)) {

                preparedStatement.setString(1, studentID);
                preparedStatement.setString(2, membername);
                preparedStatement.setString(3, password);
                preparedStatement.setInt(4, year);
                preparedStatement.setString(5, "User");


                int rowsInserted = preparedStatement.executeUpdate();
                if (rowsInserted > 0) {
                    System.out.println("CLUBS data saved successfully!");
                }

            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        public int checkValues(String membername , String password){

            ResultSet result1 ;
            ResultSet result2 ;
            String check_username = "SELECT studentName FROM members WHERE studentName = '"+ membername + "'" ;
            String check_password = "SELECT password FROM members WHERE password = '"+ password + "'" ;
            //String checkUsernameQuery = "SELECT 1 FROM members WHERE studentName = ?";
            String checkCredentialsQuery = "SELECT 1 FROM members WHERE studentName = ? AND password = ?";
            try (Connection connection = DriverManager.getConnection(url, username, pw)){
                if (password.length() < 4) {
                    return 2; // Weak password
                }
                //check if the username already exists
                else{
                    try (PreparedStatement checkUsernameStmt  = connection.prepareStatement(check_username)) {
                        result1 = checkUsernameStmt.executeQuery();
                        if (result1.next()) {
                            return 1;           //username deja existe !
                        }
                    }
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }

            return 0;
        }
        public Boolean LoginVerification(String membername , String password){
            String selectData = "SELECT studentName, password FROM members WHERE studentName = ? and password = ?";
            try(Connection connection = DriverManager.getConnection(url, username, pw)){
                try (PreparedStatement checkCredentialsStmt = connection.prepareStatement(selectData)) {
                    checkCredentialsStmt.setString(1, membername);
                    checkCredentialsStmt.setString(2, password);
                    try (ResultSet credentialsResult = checkCredentialsStmt.executeQuery()) {
                        if (credentialsResult.next()) {
                            return true;       //ACCOUNT exist (Username and password combination exists)
                        }
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
                System.out.println("probleme in login verification !");
            }
            return false;
        }

}
