package com.example.demo.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.model.Client;

public interface ClientRepository extends MongoRepository<Client, String>{
    Client findByEmail(String email);
    Client findByAccountNumber(String AccoutNumber);
    // Client findByAccountNumberAndPin(String accountNumber, String pin);
    Client findByPin(String pin);
    boolean existsByAccountNumber(String accountNumber);
    
}
