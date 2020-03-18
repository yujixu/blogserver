package org.sang.bean.company;

import org.sang.bean.constant.Education;
import org.sang.bean.constant.MaritalStatus;
import org.sang.bean.constant.PoliticalOutlook;
import org.sang.bean.constant.Sex;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Employee {
    private int id;
    private String employeeType;
    private String name;
    private String position;
    private int deptId;
    private String entryStatus;
    private PoliticalOutlook politicalOutlook;
    private String number;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date entryDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date expiration;

    private Education education;
    private String major;
    private String university;
    private String certificate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday;
    private Sex sex;
    private String email;
    private String idNumber;
    private MaritalStatus maritalStatus;
    private String phoneNumber;
    private String registered;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getEmployeeType() {
        return employeeType;
    }

    public void setEmployeeType(String employeeType) {
        this.employeeType = employeeType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getDeptId() {
        return deptId;
    }

    public void setDeptId(int deptId) {
        this.deptId = deptId;
    }

    public String getEntryStatus() {
        return entryStatus;
    }

    public void setEntryStatus(String entryStatus) {
        this.entryStatus = entryStatus;
    }

    public PoliticalOutlook getPoliticalOutlook() {
        return politicalOutlook;
    }

    public void setPoliticalOutlook(PoliticalOutlook politicalOutlook) {
        this.politicalOutlook = politicalOutlook;
    }

    public Date getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(Date entryDate) {
        this.entryDate = entryDate;
    }

    public Date getExpiration() {
        return expiration;
    }

    public void setExpiration(Date expiration) {
        this.expiration = expiration;
    }

    public Education getEducation() {
        return education;
    }

    public void setEducation(Education education) {
        this.education = education;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public String getCertificate() {
        return certificate;
    }

    public void setCertificate(String certificate) {
        this.certificate = certificate;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public MaritalStatus getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(MaritalStatus maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getRegistered() {
        return registered;
    }

    public void setRegistered(String registered) {
        this.registered = registered;
    }
}
