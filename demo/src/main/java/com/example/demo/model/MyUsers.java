package com.example.demo.model;

import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection="Customers")
public class MyUsers {

   
    private String email;
    private String pin;

    public MyUsers(){

    }

    public MyUsers(String email, String pin){
        this.email=email;
        this.pin=pin;
    }

    public String getEmail(){return email;}
    public void setEmail(String email){this.email=email;}

    public String getPin(){return pin;}
    public void setPin(String pin){this.pin=pin;}


   

}

