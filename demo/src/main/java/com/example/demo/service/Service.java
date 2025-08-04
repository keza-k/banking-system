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
                return  "Error: Deposit failed - " + e.getMessage();
            }
        }

        public static String processWithdraw(Transaction transaction){
            try {
            ArrayList<Client> clients = Constants.createClients();

            Client targetClient = null;

            for(Client Clients:clients){
                if (Clients.getAccountNumber().equals(transaction.getAccountNumber())){
                    targetClient=Clients;
                    break;
                }
                }

                if(targetClient==null){
                    transaction.setStatus("FALED");
                    return "Error: Account not found";
                }

                if(targetClient.getPin()!=transaction.getPin()){
                    transaction.setStatus("FAILED");
                    return "Incorrect Pin";
                }

                if(targetClient.getAmount()< transaction.getAmount()){
                    transaction.setStatus("FAILED");
                    return "Not enough funds to carry out transaction";
                }
                
                double accBalance = targetClient.getAmount();//previous account balance
                double newBalance = targetClient.getAmount() - transaction.getAmount();
                double accNewBalance = newBalance;
                targetClient.setAmount(accNewBalance);//current account balance
                transaction.setAmount(newBalance);//current user balance
                transaction.setStatus("SUCCESS");

                return String.format("Withdraw Successful!\n"+
                                        "AccountHolder %s\n"+
                                        "Transaction ID %s\n"+
                                        "Amount Withdrawn RWF%.2f\n"+
                                        "Your new Balance is RWF%.2f\n"+
                                        "Your previous account balance was RWF%.2f\n"+
                                        "Your current accout balance is RWF%.2f",
                                        targetClient.getName(),
                                        transaction.getId(),
                                        transaction.getAmount(),
                                        newBalance,
                                        accBalance,
                                        accNewBalance);

            }
             catch (Exception e) {
                transaction.setStatus("FAILED");
                return "Error: Withdraw failed - ";
            }
        
        }

        public static String processTransfer(Transaction transfer){

            ArrayList<Client> clients1= Constants.createClients();
            ArrayList<Client> clients2= Constants.createClients();
            try {
                
            Client senderClient1 = null;
            Client receiverClient2 = null;

            for(Client client:clients1){
                if (client.getAccountNumber().equals(transfer.getAccountNumber())){
                    senderClient1=client;
                }
            }
            for(Client Client:clients2){
                if (Client.getAccountNumber().equals(transfer.getAccountNumber())){
                    receiverClient2=Client;
                    break;
                }
            }

                if(senderClient1==null){
                    transfer.setStatus("FAILED");
                    return "Account not found";
                }
                if(receiverClient2==null){
                    transfer.setStatus("FAILED");
                    return "Account not found";
                }
                if(senderClient1.getPin()!=transfer.getPin()){
                    transfer.setStatus("FAILED");
                    return "Incorrect pin";
                }
                if (senderClient1.getAmount()<=transfer.getAmount()) {
                    transfer.setStatus("FAILED");
                    return "Not enough funds to carry out transaction";
                }
                
                double currentbalance = senderClient1.getAmount();//sender's previous account balance
                double newbalance = senderClient1.getAmount()-transfer.getAmount();//sender's current account balance
                double currenttBalance = receiverClient2.getAmount();// receiver's previous account balance
                double newwBalance = receiverClient2.getAmount()+transfer.getAmount();//receiver's current account balance
                transfer.setStatus("SUCCESS!");

                return String.format("Transfer Successful\n"+
                                    "Sender's Account Holder: %s\n"+
                                    "Receiver's Account Holder:%s\n"+
                                    "Transaction ID: %s\n"+
                                    "Amount Transfered: %.2f\n"+
                                    "Your previous balance was: %.2f\n"+
                                    "Your new balance is: %,2f\n",
                                    senderClient1.getName(),
                                    receiverClient2.getName(),
                                    transfer.getId(),
                                    transfer.getAmount(),
                                    currentbalance,
                                    newbalance);
        
        } 
        catch (Exception e) {
            transfer.setStatus("FAILED");
            return "Error: Transfer failed";
        }
    }
}

