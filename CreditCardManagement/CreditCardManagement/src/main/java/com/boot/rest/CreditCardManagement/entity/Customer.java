package com.boot.rest.CreditCardManagement.entity;

import org.springframework.data.annotation.Id;

import java.util.Date;

public class Customer {

    private String first;
    private String last;
    private String gender;
    private String job;
    private Date dob;
    @Id
    private long customer_id;

    @Override
    public String toString() {
        return "Customer{" +
                "first='" + first + '\'' +
                ", last='" + last + '\'' +
                ", gender='" + gender + '\'' +
                ", job='" + job + '\'' +
                ", dob=" + dob +
                ", customer_id=" + customer_id +
                '}';
    }

    public Customer() {
    }

    public Customer(String first, String last, String gender, String job, Date dob, long customer_id) {
        this.first = first;
        this.last = last;
        this.gender = gender;
        this.job = job;
        this.dob = dob;
        this.customer_id = customer_id;
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

    public long getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(long customer_id) {
        this.customer_id = customer_id;
    }
}
