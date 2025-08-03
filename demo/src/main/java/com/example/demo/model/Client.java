package com.example.demo.model;

public class Client {
     private String Name;
     private String ID;
     private String accountNumber;
     private double Amount;
     private int Pin;

        public Client(String name, String ID, String accountNumber, double amount, int pin ) {
            this.Name=name;
            this.ID=ID;
            this.accountNumber=accountNumber;
            this.Amount=amount;
            this.Pin=pin;
    }

    public String getName(){return Name;}
    public void setName(String name){this.Name=name;}

    public String getID(){return ID;}
    public void setID(String ID){this.ID=ID;}

    public String getAccountNumber(){return accountNumber;}
    public void setAccountNumber(String accountNumber){this.Name=accountNumber;}

    public double getAmount(){return Amount;}
    public void setAmount(double amount){this.Amount=amount;}
    
    public int getPin(){return Pin;}
    public void setPin(int pin){this.Pin=pin;}
    

}
