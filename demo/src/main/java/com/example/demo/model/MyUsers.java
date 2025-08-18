package com.example.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection="Myusers")
public class MyUsers {

    @Id
    private String accountNumbers;
    private String pins;

    public MyUsers(){

    }

    public MyUsers(String accountNumber, String pin){
        this.accountNumbers=accountNumber;
        this.pins=pin;
    }

    public String getaccountNumber(){return accountNumbers;}
    public void setName(String accountNumber){this.accountNumbers=accountNumber;}

    public String getPin(){return pins;}
    public void setPin(String pin){this.pins=pin;}


    public String Users(Client users){
        users.setAccountNumber();
    }

}

