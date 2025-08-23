package com.example.demo.dao;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.model.Transaction;

public interface TransactionRespository extends MongoRepository<Transaction, String>{
    List<Transaction> findByAccountNumber(String accountNumber);
}
