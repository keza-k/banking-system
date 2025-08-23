package com.example.demo.model;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface MyUserRepository extends MongoRepository<MyUsers, String>{
    // MyUsers findByAccountNumberAndPin(String accountNumber, String pin);
    MyUsers findByAccountNumber(String accountNumber);
}
