package com.boot.rest.CreditCardManagement.entity;

public class StateTransactions {
    private String state;
    private double total_transactions;

    @Override
    public String toString() {
        return "StateTransactions{" +
                "state='" + state + '\'' +
                ", total_transactions=" + total_transactions +
                '}';
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public double getTotal_transactions() {
        return total_transactions;
    }

    public void setTotal_transactions(double total_transactions) {
        this.total_transactions = total_transactions;
    }

    public StateTransactions(String state, double total_transactions) {
        this.state = state;
        this.total_transactions = total_transactions;
    }
}
