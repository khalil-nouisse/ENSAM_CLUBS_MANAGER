package com.ensam.Backend.model.HR;

public class ExecutiveOffice extends Member{
    private String memberPosition ;

    public ExecutiveOffice(String memberId, String memberName,String password, int year , String memberPosition) {
        super(memberId, memberName,password, year);
        this.memberPosition = memberPosition;
    }
    public ExecutiveOffice(String memberPosition){
        super();
        this.memberPosition = memberPosition ;
    }
    public String getMemberPosition() {
        return memberPosition;
    }
    public void setMemberPosition(String memberPosition){
        this.memberPosition = memberPosition ;
    }
}
