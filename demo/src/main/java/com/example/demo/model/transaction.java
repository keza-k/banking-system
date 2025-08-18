package com.example.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="Transactions")
public class Transaction {

    @Id
    private String id;
    private String transactionType;
    private double  amount;
    private String accountNumber;
    private String pin;
    private String timestamp;
    private String status;
    private String senderAccNumber;
    private String receiverAccNumber;
    // private double balance;
    
    public Transaction(String id, String transactionType, double amount, String accountNumber, String pin, String senderAccountNumber, String receiverAccountNumber) {
        this.id = id;
        this.transactionType = transactionType;
        this.amount = amount;
        this.accountNumber = accountNumber;
        this.pin = pin;
        this.timestamp = java.time.LocalDateTime.now().toString();
        this.status = "PENDING";
        this.senderAccNumber= senderAccountNumber;
        this.receiverAccNumber = receiverAccountNumber;
        
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    
    public String getTransactionType() { return transactionType; }
    public void setTransactionType(String transactionType) { this.transactionType = transactionType; }
    
    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }
    
    public String getAccountNumber() { return accountNumber; }
    public void setAccountNumber(String accountNumber) { this.accountNumber = accountNumber; }
    
    public String getPin() { return pin; }
    public void setPin(String pin) { this.pin = pin; }
    
    public String getTimestamp() { return timestamp; }
    public void setTimestamp(String timestamp) { this.timestamp = timestamp; }
    
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getSenderAccountNumber() { return senderAccNumber; }
    public void setsenderAccountNumber(String senderAccountNumber) { this.senderAccNumber = senderAccountNumber; }

    public String getReceiverAccountNumber() { return receiverAccNumber; }
    public void setreceiverAccountNumber(String receiverAccountNumber) { this.receiverAccNumber = receiverAccountNumber; }

    public String ToString() {
        return "Account Number: "+accountNumber;
    }
    
}