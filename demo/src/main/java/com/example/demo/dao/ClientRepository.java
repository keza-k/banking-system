package com.example.demo.dao;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.model.Client;

public interface ClientRepository extends MongoRepository<Client, String>{
    Optional<Client> findByAccountNumber(String accountNumber);
}
