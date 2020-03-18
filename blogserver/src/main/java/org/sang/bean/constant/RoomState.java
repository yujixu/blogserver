package org.sang.bean.constant;

public enum RoomState {
    未租(1,"未租"),
    已租(2,"已租"),
    已售(3,"已售"),
    已订(4,"已订"),
    自留(5,"自留");
    private int status;
    private String text;

    RoomState(int status,String text){
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
