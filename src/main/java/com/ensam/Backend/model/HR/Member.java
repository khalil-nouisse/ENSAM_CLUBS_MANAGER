package com.ensam.Backend.model.HR;
import java.util.ArrayList;

public class Member {
    //member attributs
    int i =0 ;
    private String memberId ;
    private String memberName ;
    private String password ;
    private int year;
    private long memberScore ;
    private ArrayList<String> memberClubs ;
    //constructor

    public Member() {
        memberScore = 0;
        memberClubs  = new ArrayList<>();
    }

    public Member(String memberId , String memberName , String password , int year){
        this.memberId = memberId ;
        this.memberName = memberName ;
        this.password = password ;
        this.year = year ;
        //memberClubs = new ArrayList<>();      do we really need to create new list each time ?
    }

    public String getMemberId(){
        return memberId ;
    }
    public String getMemberName(){
        return memberName ;
    }
    public int getYear(){
        return year;
    }
    public long getMemberScore(){
        return memberScore ;
    }
    public void setMemberId(String memberId){
        this.memberId = memberId ;
    }
    public void setMemberName(String memberName){
        this.memberName = memberName ;
    }
    public void setYear(int year){this.year = year;}
    public void setMemberScore(long memberScore){this.memberScore = memberScore ;}

    public boolean setMemberClubs(String memberClubs) {return this.memberClubs.add(memberClubs);}

    //methodes


    //display

    public void displayMember(){
        System.out.println("\nmember ID = "+ memberId
                +"\n member name : "+ memberName
                +"\nmember Grad : "+ year
                +"\nmember Score :  "+ memberScore);
        for(String string : memberClubs) {
            System.out.println("\nclub: " + string);
        }
    }

}