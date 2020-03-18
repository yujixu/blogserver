package org.sang.bean.constant;

public enum  PoliticalOutlook {
    中共党员(1,"中共党员"),
    中共预备党员(2,"中共预备党员"),
    共青团员(3,"共青团员"),
    民革党员(4,"民革党员"),
    民盟盟员(5,"民盟盟员"),
    民建会员(6,"民建会员"),
    民进会员(7,"民进会员"),
    农工党党员(8,"农工党党员"),
    致公党党员(9,"致公党党员"),
    九三学社社员(10,"九三学社社员"),
    台盟盟员(11,"台盟盟员"),
    无党派人士(12,"无党派人士"),
    群众(13,"群众");
    private int status;
    private String text;

    PoliticalOutlook(int status,String text){
        this.status=status;
        this.text=text;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
