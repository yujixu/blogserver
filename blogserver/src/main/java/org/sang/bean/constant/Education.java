package org.sang.bean.constant;

public enum  Education {
    职高(1,"职高"),
    中专(2,"中专"),
    博士(3,"博士"),
    硕士(4,"硕士"),
    专科(5,"专科"),
    本科(6,"本科"),
    高中(7,"高中"),
    初中(8,"初中"),
    小学(9,"小学");
    private int status;
    private String text;

    Education(int status,String text){
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
