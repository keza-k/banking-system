package com.example.demo.service;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.ClientRepository;
import com.example.demo.model.Client;
import com.example.demo.model.Transaction;

@Service 
public class Balance {

    @Autowired
    private ClientRepository clientRepository;

    public String processBalance(Transaction getBalance){
        Scanner scanner= new Scanner(System.in);
        System.out.println("Enter your account number");
        String accNumber= scanner.nextLine();

        System.out.println("Enter Your pin");
        String confpin = scanner.nextLine();

        getBalance.setAccountNumber(accNumber);
        getBalance.setPin(confpin);

        return findBalance(getBalance);    
    }

    public String findBalance(Transaction balancee){
        System.out.println("Account Number: "+balancee.getAccountNumber()+" Pin: "+balancee.getPin());

        Client client = clientRepository.findByAccountNumberAndPin(balancee.getAccountNumber(), balancee.getPin());
                System.out.println("Client: "+client);
                if (client==null) {
                    balancee.setStatus("FAILED");
                    return "Invalid Account Number or Pin";
                }
                 Client targetClient = client;

                return "Your balance is " +20000;

                
    }
}

