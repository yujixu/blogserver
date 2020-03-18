package org.sang.bean.constant;

public enum  Sex {
    男(1,"男"),
    女(2,"女");
    private int status;
    private String text;

    Sex(int status,String text){
        this.status=status;
        this.text=text;
    }

    public static Sex get(int v) {
        String str = String.valueOf(v);
        return get(str);
    }

    public static Sex get(String str) {
        for (Sex e : values()) {
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
