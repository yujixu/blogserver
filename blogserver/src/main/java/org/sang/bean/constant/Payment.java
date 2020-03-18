package org.sang.bean.constant;

public enum  Payment {
    网银转账(1,"网银转账"),
    现金(2,"现金"),
    支票(2,"支票");

    private int status;
    private String text;

    Payment(int status,String text){
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
