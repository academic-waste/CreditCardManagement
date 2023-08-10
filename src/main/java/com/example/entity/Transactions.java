package com.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "transactions")
public class Transactions {
    @Id
    private String id;
    @Field("trans_date_trans_time")
    private String transDateTransTime;
    private double amt;
    @Field("trans_num")
    private int transNum;
    @Field("customer_id")
    private int customerId;
    private String city;
    private String state;
    @Field("city_population")
    private int cityPopulation;
    private String merchant;
    private String category;
    private String first;
    private String last;
    private String gender;
    private String Job;
    private String dob;

}
