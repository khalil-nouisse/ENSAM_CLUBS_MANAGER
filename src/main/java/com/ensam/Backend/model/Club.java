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
    private boolean clubState; //actif or not
    private long clubScore ;        //to delete
    private String clubDescription ;
    private ArrayList<String> ClubEvent ;
    private ArrayList<Member> members ;
    private ArrayList<ExecutiveOffice> office ;

    //constructor
    public Club(){
        clubState = true;
        clubScore = 0;
        clubDescription = "null";

    }
    public Club(long clubID ,String clubName, String clubCategory, int clubMembers, boolean clubState) {
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

    public boolean isClubState() {
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

    public void setClubState(boolean clubState) {
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
    //display !
    @Override
    public String toString(){
        String S ;
        if(clubState == true){
            S = "actif";
        }
        else{
            S = "inactif";
        }
        return S+"ID = "+ clubId
                +"\nClub Name: "+ clubName
                +"\nCategory : "+ clubCategory
                +"\nmembers  : "+ MembersNumber
                +"\nstate    : "+ S
                +"\nScore    : "+ clubScore ;
    }
    public void displayClub(){
        String S ;
        if(clubState == true){
            S = "actif";
        }
        else{
            S = "inactif";
        }
        System.out.println(
                "ID = "+ clubId
                        +"\nClub Name: "+ clubName
                        +"\nCategory : "+ clubCategory
                        +"\nmembers  : "+ MembersNumber
                        +"\nstate    : "+ S
                        +"\nScore    : "+ clubScore);
    }
    /*public void addMembersFromManager(Member[] membersList) {
        for (int i = 0; i < membersList.length; i++) {
                members[clubMembers +i+1] = membersList[i];  // Copy member from MembersManager to club
            }
        }*/
}

