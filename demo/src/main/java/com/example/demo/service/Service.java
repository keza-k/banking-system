package com.example.demo.service;
import java.util.ArrayList;

import com.example.demo.helper.Constants;
import com.example.demo.model.Client;
import com.example.demo.model.Transaction;

public class Service {

    public static String bankingmenu(){
        StringBuilder getMenu=new StringBuilder();
        getMenu.append("*************************\n        K Banking       \n*************************\nWelcome to K Banking System\n0. Register\n1. Deposit\n2. Withdraw\n3. Check Balance\n4. Reset Pin\n5. Mini Statement");
        return getMenu.toString();
    }
    public static String respondMenu(int choices){
         String menuResponse;
        switch (choices) {
            case 0:
                menuResponse="You selected: Register";
                break;
            case 1:
                menuResponse="You selected: Deposit";
                break;
            case 2:
                menuResponse="You selected: Withdraw";
                break;
            case 3:
                menuResponse="You selected: Check balance";
                break;
            case 4:
                menuResponse="You selected: Reset Pin";
                break;
            case 5:
                menuResponse="You selected: Mini Statement";
                break;
            default:
                menuResponse="Imput a valid option";
        }
        return menuResponse;
        
        }

        public static String processDeposit(Transaction transaction) {
            try {
                ArrayList<Client> clients = Constants.createClients();

                Client targetClient = null;
                for (Client client : clients) {
                    if (client.getAccountNumber().equals(transaction.getAccountNumber())) {
                        targetClient = client;
                        break;
                    }
                }

                if (targetClient == null) {
                    transaction.setStatus("FAILED");
                    return  "Error: Account not found";
                }

                if (targetClient.getPin() != transaction.getPin()) {
                    transaction.setStatus("FAILED");
                    return "Error: Invalid PIN";
                }

                if (transaction.getAmount() <= 0) {
                    transaction.setStatus("FAILED");
                    return "Error: Amount must be greater than 0";
                }

                double currentBalance = targetClient.getAmount();
                double newBalance = currentBalance + transaction.getAmount();
                targetClient.setAmount(newBalance);
                transaction.setStatus("SUCCESS");

                return String.format("Deposit Successful!\n" +
                                "Account Holder: %s\n" +
                                "Transaction ID: %s\n" +
                                "Amount Deposited: RWF%.2f\n" +
                                "Previous Balance: RWF%.2f\n" +
                                "New Balance: RWF%.2f", 
                                targetClient.getName(),
                                transaction.getId(), 
                                transaction.getAmount(), 
                                currentBalance,
                                newBalance);


            } catch (Exception e) {
                transaction.setStatus("FAILED");
                return  "Error: Transaction failed - " + e.getMessage();
            }
        }
    
    
    }

