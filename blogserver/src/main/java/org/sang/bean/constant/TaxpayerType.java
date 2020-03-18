package org.sang.bean.constant;

public enum TaxpayerType {
    自然人(1,"自然人"),
    法人(2,"法人"),
    个体工商户(3,"个体工商户");
    private int status;
    private String text;

    TaxpayerType(int status,String text){
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
