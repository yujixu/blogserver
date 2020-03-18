package org.sang.bean.constant;

public enum MaritalStatus {
    已婚(1,"已婚"),
    未婚(2,"未婚");
    private int status;
    private String text;

    MaritalStatus(int status,String text){
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
