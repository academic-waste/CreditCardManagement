package com.boot.rest.CreditCardManagement.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
@Document
public class Customer {
    @Id
    private String id;
    private long customerId;
    private String first;
    private String last;
    private String gender;
    private String job;
    @JsonFormat(pattern="EEE MMM dd yyyy HH:mm:ss 'GMT'Z",timezone = "GMT+8")
    private Date dob;


    @Override
    public String toString() {
        return "Customer{" +
                "customerId=" + customerId +
                ", first='" + first + '\'' +
                ", last='" + last + '\'' +
                ", gender='" + gender + '\'' +
                ", job='" + job + '\'' +
                ", dob=" + dob +
                '}';
    }

    public Customer() {
    }

    public Customer(long customerId, String first, String last, String gender, String job, Date dob) {
        this.customerId = customerId;
        this.first = first;
        this.last = last;
        this.gender = gender;
        this.job = job;
        this.dob = dob;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getCustomerId() {
        return this.customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
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

    public Date getDob(Date dob) {
        return this.dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }
//@Field("customer_id")



}
