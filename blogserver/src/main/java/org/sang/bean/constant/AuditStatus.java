package org.sang.bean.constant;

public enum AuditStatus {
    已审核(1,"已审核"),
    待审核(2,"待审核"),
    已驳回(3,"已驳回");
    private int status;
    private String text;

    AuditStatus(int status,String text){
        this.status=status;
        this.text=text;
    }

    public static AuditStatus get(int v) {
        String str = String.valueOf(v);
        return get(str);
    }

    public static AuditStatus get(String str) {
        for (AuditStatus e : values()) {
            if(e.toString().equals(str)) {
                return e;
            }
        }
        return null;
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
