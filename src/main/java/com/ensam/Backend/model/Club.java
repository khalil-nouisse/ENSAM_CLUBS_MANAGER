package com.ensam.Backend.model;

import com.ensam.Backend.model.HR.ExecutiveOffice;
import com.ensam.Backend.model.HR.Member;
import java.util.ArrayList;

public class Club {
    //attributs
    private long clubId ;
    private String clubName ;
    private String clubCategory ;
    private int MembersNumber ;
    private String clubState; //actif or not
    private long clubScore ;        //to delete
    private String clubDescription ;
    private ArrayList<String> ClubEvent ;
    private ArrayList<Member> members ;
    private ArrayList<ExecutiveOffice> office ;
    private String clubImage;

    //constructor
    public Club(){
        clubState = "Active";
        clubScore = 0;
        clubDescription = "null";

    }
    public Club(String clubName , String clubCategory , String clubState , String clubDescription , String clubImage){
        this.clubName = clubName ;
        this.clubCategory = clubCategory ;
        this.clubState = clubState ;
        this.clubDescription = clubDescription ;
        this.clubImage = clubImage ;
    }
    public Club(long clubId ,String clubName , String clubCategory , String clubState , String clubDescription ,String clubImage){
        this.clubId = clubId;
        this.clubName = clubName ;
        this.clubCategory = clubCategory ;
        this.clubState = clubState ;
        this.clubDescription = clubDescription ;
        this.clubImage = clubImage ;
    }
    public Club(long clubID ,String clubName, String clubCategory, int clubMembers, String clubState) {
        this.clubId = clubID ;
        this.clubName = clubName ;
        this.clubCategory = clubCategory ;
        this.MembersNumber = clubMembers ;
        this.clubState = clubState ;
        ClubEvent = new ArrayList<>();
        members = new ArrayList<>();
        office = new ArrayList<>();
    }
    @Override
    public boolean equals(Object obj){
        if(this == obj)return true ;
        if(obj == null || getClass() != obj.getClass())return false ;
        Club club = (Club) obj ;
        return  this.clubName.equals(club.clubName);
    }
    //getters and setters
    public long getClubID() {
        return clubId ;
    }
    public String getClubName(){
        return clubName ;
    }
    public String getClubCategory() {
        return clubCategory;
    }

    public long getClubMembers() {
        return MembersNumber;
    }

    public ArrayList<Member> getMembers() {
        return members  ;
    }

    public String isClubState() {
        return clubState;
    }

    public long getClubScore() {
        return clubScore;
    }

    public String getClubDescription() {
        return clubDescription;
    }

    public ArrayList<String> getClubEvents() {
        return ClubEvent;
    }

    public void setClubId(long clubId) {
        this.clubId = clubId;
    }
    public void setMembers(ArrayList<Member> members) {
        this.members = members;
    }

    public void setClubName(String clubName){
        this.clubName = clubName ;
    }
    public void setClubCategory(String clubCategory) {
        this.clubCategory = clubCategory;
    }

    public void setClubMembers(int clubMembers) {
        this.MembersNumber = clubMembers;
    }

    public void setClubState(String clubState) {
        this.clubState = clubState;
    }

    public void setClubScore(long clubScore) {
        this.clubScore = clubScore;
    }

    public void setClubDescription(String clubDescription) {
        this.clubDescription = clubDescription;
    }

    public void setClubEvents(ArrayList<String> clubEvents) {
        this.ClubEvent = ClubEvent ;
    }

    public String getClubImage() {
        return clubImage;
    }

    public void setClubImage(String clubImage) {
        this.clubImage = clubImage;
    }

    //display !
    @Override
    public String toString(){
        return "ID = "+ clubId
                +"\nClub Name: "+ clubName
                +"\nCategory : "+ clubCategory
                +"\nmembers  : "+ MembersNumber
                +"\nstate    : "+ clubState
                +"\nScore    : "+ clubScore ;
    }
    public void displayClub(){
        System.out.println(
                "ID = "+ clubId
                        +"\nClub Name: "+ clubName
                        +"\nCategory : "+ clubCategory
                        +"\nmembers  : "+ MembersNumber
                        +"\nstate    : "+ clubState
                        +"\nScore    : "+ clubScore);
    }

}

