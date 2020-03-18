package org.sang.bean.purchase.qo;

import org.sang.bean.constant.Payment;
import org.sang.bean.constant.State;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

public class PurContractQo {
    private int id;
    private String name;
    private String number;
    private String purType;
    private int supplierId;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date signingdate;
    private int employeeId;
    private Payment payment;
    private BigDecimal amount;
    private State state;
    private int purreqId;
    private String purReqName;

    public String getPurReqName() {
        return purReqName;
    }

    public void setPurReqName(String purReqName) {
        this.purReqName = purReqName;
    }

    public int getPurreqId() {
        return purreqId;
    }

    public void setPurreqId(int purreqId) {
        this.purreqId = purreqId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getPurType() {
        return purType;
    }

    public void setPurType(String purType) {
        this.purType = purType;
    }

    public int getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(int supplierId) {
        this.supplierId = supplierId;
    }

    public Date getSigningdate() {
        return signingdate;
    }

    public void setSigningdate(Date signingdate) {
        this.signingdate = signingdate;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }
}
