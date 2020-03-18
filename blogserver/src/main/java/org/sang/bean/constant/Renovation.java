package org.sang.bean.constant;

public enum Renovation {
    毛胚(1,"毛胚"),
    精装(2,"精装"),
    简装(3,"简装");
    private int status;
    private String text;

    Renovation(int status,String text){
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
