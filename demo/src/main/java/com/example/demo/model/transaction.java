package com.example.demo.model;

public class Transaction {
    private String id;
    private String transactionType;
    private double  amount;
    private String accountNumber;
    private int pin;
    private String timestamp;
    private String status;
    
    public Transaction(String id, String transactionType, double amount, String accountNumber, int pin) {
        this.id = id;
        this.transactionType = transactionType;
        this.amount = amount;
        this.accountNumber = accountNumber;
        this.pin = pin;
        this.timestamp = java.time.LocalDateTime.now().toString();
        this.status = "PENDING";
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    
    public String getTransactionType() { return transactionType; }
    public void setTransactionType(String transactionType) { this.transactionType = transactionType; }
    
    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }
    
    public String getAccountNumber() { return accountNumber; }
    public void setAccountNumber(String accountNumber) { this.accountNumber = accountNumber; }
    
    public int getPin() { return pin; }
    public void setPin(int pin) { this.pin = pin; }
    
    public String getTimestamp() { return timestamp; }
    public void setTimestamp(String timestamp) { this.timestamp = timestamp; }
    
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}