package com.boot.rest.CreditCardManagement.entity;

public class CategoryTransactions {

    private String category;
    private double total_transactions;

    @Override
    public String toString() {
        return "CategoryTransactions{" +
                "category='" + category + '\'' +
                ", total_transactions=" + total_transactions +
                '}';
    }

    public CategoryTransactions(String category, double total_transactions) {
        this.category = category;
        this.total_transactions = total_transactions;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getTotal_transactions() {
        return total_transactions;
    }

    public void setTotal_transactions(double total_transactions) {
        this.total_transactions = total_transactions;
    }
}
