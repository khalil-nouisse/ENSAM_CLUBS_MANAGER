package com.ensam.Backend.manager;

import com.ensam.Backend.model.Club;

public class  ClubManager {
    public static final int MAX = 100;
    private int numCLubs = 0;
    private Club[] clubTab = new Club[MAX];

    //getters and setters :


    public int getNumCLubs() {
        return numCLubs;
    }

    public Club[] getClubTab() {
        return clubTab;
    }

    //recherche du club en utulisant Club
    public Club getClub(String name){
        for(int i = 0 ; i < numCLubs ; i++){
            if(clubTab[i].getClubName().equals(name)){
                return clubTab[i];
            }
        }
        System.out.println("ce CLUB n exite pas?!");
        return null;
    }
    //recherche du club par Club ID
    public Club getClub(long clubId){
        for(Club i : clubTab){
            if(i == null){
                System.out.println("ce ID ne correspond a aucun club !");
                break;
            }
            if(i.getClubID() == clubId){
                return i;
            }
        }

        return null ;
    }
    //recherche du club en utulisant Club
    public Club getClub(Club club1){
        for(Club i : clubTab){
            if(i == null){break;}
            if(i.equals(club1)){
                return i;
            }
        }
        return null;
    }
    //recherche du club en utulisant Club
    public int getClubIndex(String name){
        for(int i = 0 ; i < numCLubs ; i++){
            if(clubTab[i].getClubName().equals(name)){
                return i ;
            }
        }
        return -1 ;
    }
    public Boolean addClub(Club club){

        if(getClub(club)!= null || (numCLubs + 1)>=MAX){
            System.out.println("Ce Club deja existe !");
            return false ;
        }
        club.setClubId(numCLubs + 1);
        this.clubTab[numCLubs++] = club ;


        return true ;
    }
    /*public void deleteClubByName(String clubName){
        int index = getClubIndex(clubName) ;
        club[index] = null ;
        numCLubs-- ;
        getClub(clubName).setClubId(numCLubs);
        for ( int i = index ; i < numCLubs ; i++ ){
            club[i] = club[i+1] ;
        }
    }*/
    public void deleteClubByName(String clubName){
        int index = getClubIndex(clubName);
        if(index >= 0){
            clubTab[index] = clubTab[numCLubs-1];
            clubTab[numCLubs] = null ;
        }
        numCLubs-- ;
    }
    public void displayClubs(){
        for(int i=0 ; i<numCLubs ; i++){
            System.out.println("\n========== CLUB :" +(i+1)+"==========");
            clubTab[i].displayClub();

        }
    }
    /*public void updateCLubMembers(String clubName , int numMembers){
        int index = getClubIndex(clubName);
        if(index != 0){
            getClub(clubName).setClubMembers(numMembers);
        }
    }*/

}

