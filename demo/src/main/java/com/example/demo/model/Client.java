package com.example.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Document(collection ="Customers")
public final  class Client {

    @Id
     private String id;
     private String Name;
     private String accountNumber;
     private double Amount;
     private String pin;
     private String status;
     private String email;



     public Client(){

     }
        public Client(String Name, double amount, String pin, String email ) {
            this.Name=Name;
            this.id= generateSequentialID(4);
            this.accountNumber=randomSequentialVal(6);
            this.Amount=amount;
            this.pin=pin;
            this.status = "PENDING";
            this.email = email;

        }
    
        

    public String getName(){return Name;}
    public void setName(String Name){this.Name=Name;}

    public String getID(){return id;}
    public void setID(String ID){this.id=ID;}

    public String getAccountNumber(){return accountNumber;}
    public void setAccountNumber(String accountNumber){this.accountNumber=accountNumber;}

    public double getAmount(){return Amount;}
    public void setAmount(double amount){this.Amount=amount;}
    
    public String getPin(){return pin;}
    public void setPin(String pin){this.pin=pin;}

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }





    private static int counterr = 10000;

    public synchronized String randomSequentialVal(int length){
        counterr++;
    String uuid= String.valueOf(counterr); 
    while (uuid.length() < length){
        uuid = "0" + uuid; 
    }
    return uuid;
 
    }

    private static int counter = 0000;

    public synchronized String generateSequentialID(int length) {
        counter++;
        String Id = String.valueOf(counter);
        while (Id.length() < length) {
            Id = "0" + Id;
        }
        return Id;
    }

    public String hashPin(Client client){

    PasswordEncoder Pin = new BCryptPasswordEncoder();
       String hashedPin = Pin.encode(pin);
       boolean matches = Pin.matches(pin, hashedPin);

       return hashedPin;
    }
}
