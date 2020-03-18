package org.sang.bean.business;

import org.sang.bean.constant.Renovation;
import org.sang.bean.constant.RoomState;

import java.math.BigDecimal;

public class RoomResource {
    private int id;
    private int roomId;
    private BigDecimal area;
    private BigDecimal rent;
    private Renovation renovation;
    private RoomState state;
    private BigDecimal propertyFee;
    private BigDecimal broadbandFee;
    private BigDecimal elevatorFee;
    private BigDecimal electricCharge;
    private BigDecimal waterCharge;
    private String introduce;

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public BigDecimal getArea() {
        return area;
    }

    public void setArea(BigDecimal area) {
        this.area = area;
    }

    public BigDecimal getRent() {
        return rent;
    }

    public void setRent(BigDecimal rent) {
        this.rent = rent;
    }

    public Renovation getRenovation() {
        return renovation;
    }

    public void setRenovation(Renovation renovation) {
        this.renovation = renovation;
    }

    public RoomState getState() {
        return state;
    }

    public void setState(RoomState state) {
        this.state = state;
    }

    public BigDecimal getPropertyFee() {
        return propertyFee;
    }

    public void setPropertyFee(BigDecimal propertyFee) {
        this.propertyFee = propertyFee;
    }

    public BigDecimal getBroadbandFee() {
        return broadbandFee;
    }

    public void setBroadbandFee(BigDecimal broadbandFee) {
        this.broadbandFee = broadbandFee;
    }

    public BigDecimal getElevatorFee() {
        return elevatorFee;
    }

    public void setElevatorFee(BigDecimal elevatorFee) {
        this.elevatorFee = elevatorFee;
    }

    public BigDecimal getElectricCharge() {
        return electricCharge;
    }

    public void setElectricCharge(BigDecimal electricCharge) {
        this.electricCharge = electricCharge;
    }

    public BigDecimal getWaterCharge() {
        return waterCharge;
    }

    public void setWaterCharge(BigDecimal waterCharge) {
        this.waterCharge = waterCharge;
    }
}
