package com.example.demo.service;

import java.util.Optional;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.dao.ClientRepository;
import com.example.demo.model.Client;
import com.example.demo.model.Transaction;

public class Balance {

private final ClientRepository clientRepository;

    @Autowired
    public Balance(ClientRepository clientRepository){
        this.clientRepository = clientRepository;
    }

    public String processBalance(Transaction transaction){
        Scanner scanner= new Scanner(System.in);
        System.out.println("Enter your account number");
        String accNumber= scanner.nextLine();

        System.out.println("Enter Your pin");
        int confpin = scanner.nextInt();

        transaction.setAccountNumber(accNumber);
        transaction.setPin(confpin);

        return checkBalance(transaction);
    }

    public String checkBalance(Transaction balance){
         Optional<Client> optionalClient = clientRepository.findByAccountNumber(balance.getAccountNumber());
                if (optionalClient.isEmpty()) {
                    balance.setStatus("FAILED");
                    return "Error: Account not found";
                }

                Client targetClient=optionalClient.get();

                if(targetClient.getPin()!=balance.getPin()){
                    balance.setStatus("FAILED");
                    return "Incorrect Pin";
                }
                balance.setAmount(targetClient.getAmount());

                return String.format("Your balance is "+balance.getAmount());

    }
}
