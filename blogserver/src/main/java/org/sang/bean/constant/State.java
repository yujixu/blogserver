package org.sang.bean.constant;

public enum State {
    INPUT(1,"制单"),
    EXCUTE(2,"下达"),
    CLOSE(3,"关闭");
    private int status;
    private String text;

    State(int status,String text){
        this.status=status;
        this.text=text;
    }

    public static State get(int v) {
        String str = String.valueOf(v);
        return get(str);
    }

    public static State get(String str) {
        for (State e : values()) {
            if(e.toString().equals(str)) {
                return e;
            }
        }
        return null;
    }

    public int getStatus() {
        return status;
    }

    public String getText() {
        return text;
    }
}