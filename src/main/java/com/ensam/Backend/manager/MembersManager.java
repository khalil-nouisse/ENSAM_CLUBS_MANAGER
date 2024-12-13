package com.ensam.Backend.manager;
import com.ensam.Backend.model.HR.Member;
import java.util.ArrayList ;

public class MembersManager {


    private ArrayList<Member> membersList ;

    public MembersManager() {
        membersList = new ArrayList<>() ;
    }
    public Member searchMemberById1(long Id){
        for(Member S : membersList){
            if (S.getMemberId().equals(Id)){
                return S ;
            }
        }
        return null ;
    }
    public void addMember(Member member){          // i think this one must be in club manager !
        if(!membersList.contains(member) ){
            //member.setMemberId(membersList.size());       owe dont want to change memberID , it's constant
            membersList.add(member) ;
        }
        else{
            System.out.println("deja existe !");
        }
    }


    /* public void deletMemberById(long Id){
       int index = searchMemberById(Id) ;
        if(index != 0){
            membersList[index] = null ;
            numMembers -- ;
            for(int i=index ; i < numMembers ; i++ ){
                membersList[i] = membersList[i+1];
            }
        }
        else {
            System.out.println("this member doesn't exist !");
        }
    }*/
    public void  deletMemberById(int Id){
        if(membersList.contains(searchMemberById1(Id)))    membersList.remove(searchMemberById1(Id));
    }
    public void displayMembers(){
        for(Member M : membersList){
            System.out.println("\n========= MEMBER :  =========");
            M.displayMember();
        }
    }
}