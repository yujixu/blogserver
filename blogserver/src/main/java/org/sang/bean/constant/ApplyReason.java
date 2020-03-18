package org.sang.bean.constant;

public enum ApplyReason {
    办公使用(1,"办公使用"),
    维修使用(2,"维修使用");
    private int status;
    private String text;

    ApplyReason(int status,String text){
        this.status=status;
        this.text=text;
    }

    public static ApplyReason get(int v) {
        String str = String.valueOf(v);
        return get(str);
    }

    public static ApplyReason get(String str) {
        for (ApplyReason e : values()) {
            if(e.toString().equals(str)) {
                return e;
            }
        }
        return null;
    }
}
