package com.example.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection ="Customers")
public class Client {

    @Id
     private String id;
     private String name;
     private String accountNumber;
     private double Amount;
     private String pin;

     public Client(){

     }
     

        public Client(String name, String id, String accountNumber, double amount, String pin ) {
            this.name=name;
            this.id=id;
            this.accountNumber=accountNumber;
            this.Amount=amount;
            this.pin=pin;
        }
    

    public  String getName(){return name;}
    public  void setName(String name){this.name=name;}

    public String getID(){return id;}
    public void setID(String ID){this.id=ID;}

    public String getAccountNumber(){return accountNumber;}
    public void setAccountNumber(String accountNumber){this.accountNumber=accountNumber;}

    public double getAmount(){return Amount;}
    public void setAmount(double amount){this.Amount=amount;}
    
    public String getPin(){return pin;}
    public void setPin(String pin){this.pin=pin;}
    

}
