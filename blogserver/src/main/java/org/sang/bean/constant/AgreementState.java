package org.sang.bean.constant;

public enum  AgreementState {
    未通过(1,"未通过"),
    已作废(2,"已作废"),
    已生效(2,"已生效"),
    已审核(2,"已审核"),
    待审核(2,"待审核");

    private int status;
    private String text;

    AgreementState(int status,String text){
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
