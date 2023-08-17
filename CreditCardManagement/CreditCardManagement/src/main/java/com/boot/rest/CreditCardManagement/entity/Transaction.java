package com.boot.rest.CreditCardManagement.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

@Document
public class Transaction {
    @Field("trans_date_trans_time")
    private Date transDateTransTime;
    private double amt;
    @Id
    private long transNum;
    @Field("customer_id")
    private long customerId;
    private String city;
    private String state;
    @Field("city_population")
    private long cityPopulation;
    private String merchant;
    private String category;
    private String first;
    private String last;
    private String gender;
    @Field("Job")
    private String job;
    private Date dob;

    @Override
    public String toString() {
        return "transaction{" +
                "transDateTransTime=" + transDateTransTime +
                ", amt=" + amt +
                ", transNum=" + transNum +
                ", customerId=" + customerId +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", cityPopulation=" + cityPopulation +
                ", merchant='" + merchant + '\'' +
                ", category='" + category + '\'' +
                ", first='" + first + '\'' +
                ", last='" + last + '\'' +
                ", gender='" + gender + '\'' +
                ", job='" + job + '\'' +
                ", dob=" + dob +
                '}';
    }

    public Transaction() {
    }

    public Transaction(Date transDateTransTime, double amt, long transNum, long customerId, String city, String state, long cityPopulation, String merchant, String category, String first, String last, String gender, String job, Date dob) {
        this.transDateTransTime = transDateTransTime;
        this.amt = amt;
        this.transNum = transNum;
        this.customerId = customerId;
        this.city = city;
        this.state = state;
        this.cityPopulation = cityPopulation;
        this.merchant = merchant;
        this.category = category;
        this.first = first;
        this.last = last;
        this.gender = gender;
        this.job = job;
        this.dob = dob;
    }

    public Date getTransDateTransTime() {
        return transDateTransTime;
    }

    public void setTransDateTransTime(Date transDateTransTime) {
        this.transDateTransTime = transDateTransTime;
    }

    public double getAmt() {
        return amt;
    }

    public void setAmt(double amt) {
        this.amt = amt;
    }

    public long getTransNum() {
        return transNum;
    }

    public void setTransNum(long transNum) {
        this.transNum = transNum;
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public long getCityPopulation() {
        return cityPopulation;
    }

    public void setCityPopulation(long cityPopulation) {
        this.cityPopulation = cityPopulation;
    }

    public String getMerchant() {
        return merchant;
    }

    public void setMerchant(String merchant) {
        this.merchant = merchant;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }
}
