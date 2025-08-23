package com.example.demo.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.model.Client;

public interface ClientRepository extends MongoRepository<Client, String>{
    Client findByAccountNumber(String accountNumber);
    // Client findByAccountNumberAndPin(String accountNumber, String pin);
    Client findByPin(String pin);
    boolean existsByAccountNumber(String accountNumber);
    
}
