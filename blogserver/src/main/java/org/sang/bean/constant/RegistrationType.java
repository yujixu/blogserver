package org.sang.bean.constant;

public enum RegistrationType {
    外商投资企业(1,"外商投资企业"),
    港澳台商投资企业(2,"港澳台商投资企业"),
    内资企业(3,"内资企业");
    private int status;
    private String text;

    RegistrationType(int status,String text){
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
