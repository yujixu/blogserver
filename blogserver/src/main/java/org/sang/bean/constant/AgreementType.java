package org.sang.bean.constant;

public enum AgreementType {
    续租(1,"续租"),
    换租(2,"换租"),
    新租(2,"新租"),
    扩租(2,"扩租");

    private int status;
    private String text;

    AgreementType(int status,String text){
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
