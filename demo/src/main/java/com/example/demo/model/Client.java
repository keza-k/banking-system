package com.example.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection ="Customers")
public class Client {

    @Id
     private String id;
     private String Name;
     private String accountNumber;
     private double Amount;
     private int Pin;

     public Client(){

     }
     

        public Client(String name, String id, String accountNumber, double amount, int pin ) {
            this.Name=name;
            this.id=id;
            this.accountNumber=accountNumber;
            this.Amount=amount;
            this.Pin=pin;
        }
    

    public String getName(){return Name;}
    public void setName(String name){this.Name=name;}

    public String getID(){return id;}
    public void setID(String ID){this.id=ID;}

    public String getAccountNumber(){return accountNumber;}
    public void setAccountNumber(String accountNumber){this.accountNumber=accountNumber;}

    public double getAmount(){return Amount;}
    public void setAmount(double amount){this.Amount=amount;}
    
    public int getPin(){return Pin;}
    public void setPin(int pin){this.Pin=pin;}
    

}
