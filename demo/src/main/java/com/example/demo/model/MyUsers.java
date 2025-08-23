package com.example.demo.model;

import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection="Customers")
public class MyUsers {

   
    private String accountNumber;
    private String pin;

    public MyUsers(){

    }

    public MyUsers(String accountNumber, String pin){
        this.accountNumber=accountNumber;
        this.pin=pin;
    }

    public String getAccountNumber(){return accountNumber;}
    public void setAccountNumber(String accountNumber){this.accountNumber=accountNumber;}

    public String getPin(){return pin;}
    public void setPin(String pin){this.pin=pin;}


   

}

