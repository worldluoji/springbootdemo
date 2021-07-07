package com.es.demo.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(indexName = "bank")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Bank {
    @Id
    @Field(value="account_number")
    private String accountNumber;
    @Field(value="balance")
    private String balance;
    @Field(value="firstname")
    private String firstname;
    @Field(value="lastname")
    private String lastname;
    @Field(value="age")
    private int age;
    @Field(value="gender")
    private String gender;
    @Field(value="address")
    private String address;
    @Field(value="employer")
    private String employer;
    @Field(value="email")
    private String email;
    @Field(value="city")
    private String city;
    @Field(value="state")
    private String state;
}
