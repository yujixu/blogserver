package org.sang.bean.warehouse;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.sang.bean.constant.State;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class WarehouseReceipt {
    private int id;
    private String name;
    private String number;
    private int contractId;
    private int consigneeId;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date receiptdate;

    private State state;

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
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

    public int getContractId() {
        return contractId;
    }

    public void setContractId(int contractId) {
        this.contractId = contractId;
    }

    public int getConsigneeId() {
        return consigneeId;
    }

    public void setConsigneeId(int consigneeId) {
        this.consigneeId = consigneeId;
    }

    public Date getReceiptdate() {
        return receiptdate;
    }

    public void setReceiptdate(Date receiptdate) {
        this.receiptdate = receiptdate;
    }
}
