package com.example.demo.service;
import java.util.Optional;
import java.util.Scanner;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.ClientRepository;
import com.example.demo.model.Client;
import com.example.demo.model.Transaction;

@Service
public class BankingService {

    private final ClientRepository clientRepository;

    @Autowired
    public BankingService(ClientRepository clientRepository){
        this.clientRepository = clientRepository;
    }

    public static String bankingmenu(){
        StringBuilder getMenu=new StringBuilder();
        getMenu.append("*************************\n        K Banking       \n*************************\nWelcome to K Banking System\n0. Register\n1. Deposit\n2. Withdraw\n3.Transfer\n4. Check Balance\n5. Reset Pin\n6. Mini Statement");
        return getMenu.toString();
    }
    public String respondMenu(int choices){
    
         String menuResponse;
        switch (choices) {
            case 0:
                menuResponse="You selected: Register";
                break;
            case 1:
                    menuResponse="--Deposit--";
                break;
            case 2:
                menuResponse="You selected: Withdraw";
                break;
            case 3:
                menuResponse="You selected: Tranfer";
                break;
            case 4:
                menuResponse="You selected: Check Balance";
                break;
            case 5:
                menuResponse="You selected: Reset Pin";
                break;
            case 6:
                menuResponse="You selected: Mini Statements";
            default:
                menuResponse="Imput a valid option";
        }
        return menuResponse;
        
        }

        public String handleDeposits(Transaction transaction){
        Scanner scanner= new Scanner(System.in);
        System.out.print("Enter account number: ");
        String accNumber = scanner.nextLine();

        System.out.print("Enter amount to deposit: ");
        double cashd = scanner.nextDouble();

        System.out.print("Enter PIN: ");
        int confpin = scanner.nextInt();
        scanner.nextLine(); // consume newline

        transaction.setAccountNumber(accNumber);
        transaction.setAmount(cashd);
        transaction.setPin(confpin);
        transaction.setId(UUID.randomUUID().toString());

        return processDeposit(transaction);
        }

        public String processDeposit(Transaction transaction) { 
            try {
                Optional<Client> optionalClient = clientRepository.findByAccountNumber(transaction.getAccountNumber());
                if (optionalClient.isEmpty()) {
                    transaction.setStatus("FAILED");
                    return "Error: Account not found";
                }

                Client targetClient = optionalClient.get();

                
                System.out.println("==Deposit==");

                //Find client
                // Client targetClient = null;
                // for (Client client : clients) {
                //     if (client.getAccountNumber().equals(transaction.getAccountNumber())) {
                //         targetClient = client;
                //         break;
                //     }
                // }
                // //Incase the account number is not found.
                // if (targetClient == null) {
                //     transaction.setStatus("FAILED");
                //     return  "Error: Account not found";
                // }
                //Step2: Get the amount;
                 if (transaction.getAmount() <= 0) {
                    transaction.setStatus("FAILED");
                    return "Error: Amount must be greater than 0";
                }

                //input pin
                if (targetClient.getPin() != transaction.getPin()) {
                    transaction.setStatus("FAILED");
                    return "Error: Invalid PIN";
                }
                
                //carrying out transaction
                double currentBalance = targetClient.getAmount();
                double newBalance = currentBalance + transaction.getAmount();
                targetClient.setAmount(newBalance);
                transaction.setStatus("SUCCESS");

                return String.format("Deposit Successful!, Your new balance is "+newBalance);
                                // "Account Holder: %s\n" +
                                // "Transaction ID: %s\n" +
                                // "Amount Deposited: RWF%.2f\n" +
                                // "Previous Balance: RWF%.2f\n" +
                                // "New Balance: RWF%.2f", 
                                // targetClient.getName(),
                                // transaction.getId(), 
                                // transaction.getAmount(), 
                                // currentBalance,
                                // newBalance);


            } catch (Exception e) {
                transaction.setStatus("FAILED");
                return  "Error: Deposit failed - " + e.getMessage();
            }
        }

        public String handleWithdraws(Transaction transaction){
        Scanner scanner= new Scanner(System.in);
        System.out.print("Enter account number: ");
        String accNumber = scanner.nextLine();

        System.out.print("Enter amount to deposit: ");
        double cashw = scanner.nextDouble();

        System.out.print("Enter PIN: ");
        int confpin = scanner.nextInt();
        scanner.nextLine(); // consume newline

        transaction.setAccountNumber(accNumber);
        transaction.setAmount(cashw);
        transaction.setPin(confpin);
        transaction.setId(UUID.randomUUID().toString());

        return processWithdraw(transaction);
        }

        public String processWithdraw(Transaction transaction){
            
            try {
                Optional<Client> optionalClient = clientRepository.findByAccountNumber(transaction.getAccountNumber());
                if (optionalClient.isEmpty()) {
                    transaction.setStatus("FAILED");
                    return "Error: Account not found";
                }

                Client targetClient = optionalClient.get();            
                System.out.println("==Withdraw==");

                //entering the amount to withdraw
                if(targetClient.getAmount()< transaction.getAmount()){
                    transaction.setStatus("FAILED");
                    return "Not enough funds to carry out transaction";
                }

                //validating the pin
                if(targetClient.getPin()!=transaction.getPin()){
                    transaction.setStatus("FAILED");
                    return "Incorrect Pin";
                }
                

               //carrying out transaction
                double accPBalance = targetClient.getAmount();//previous account balance
                double newBalance = targetClient.getAmount() - transaction.getAmount();
                double accNewBalance = newBalance;
                targetClient.setAmount(accNewBalance);//current account balance
                // transaction.setAmount(newBalance);//current user balance
                transaction.setStatus("SUCCESS");

                return String.format("Withdraw Successful! You have withdrawn "+transaction.getAmount()+" Your account balance is "+accNewBalance);
                                        // "AccountHolder %s\n"+
                                        // "Transaction ID %s\n"+
                                        // "Amount Withdrawn RWF%.2f\n"+
                                        // "Your new Balance is RWF%.2f\n"+
                                        // "Your previous account balance was RWF%.2f\n"+
                                        // "Your current accout balance is RWF%.2f",
                                        // targetClient.getName(),
                                        // transaction.getId(),
                                        // transaction.getAmount(),
                                        // newBalance,
                                        // accBalance,
                                        // accNewBalance);

            }
             catch (Exception e) {
                transaction.setStatus("FAILED");
                return "Error: Withdraw failed - ";
            }
        
        }

        public String processTransfer(Transaction transfer){
                try {

                Optional<Client> optionalSender = clientRepository.findByAccountNumber(transfer.getAccountNumber());
                if (optionalSender.isEmpty()) {
                    transfer.setStatus("FAILED");
                    return "Error: Account not found";
                }

                Optional<Client> optionalReceiver = clientRepository.findByAccountNumber(transfer.getAccountNumber());
                if (optionalReceiver.isEmpty()) {
                    transfer.setStatus("FAILED");
                    return "Error: Account not found";
                }


                // Client targetClient = optionalClient.get(); 


            Client senderClient = optionalSender.get();
            Client receiverClient = optionalReceiver.get();

                if(senderClient.getPin()!=transfer.getPin()){
                    transfer.setStatus("FAILED");
                    return "Incorrect pin";
                }
                if (senderClient.getAmount()<transfer.getAmount()) {
                    transfer.setStatus("FAILED");
                    return "Not enough funds to carry out transaction";
                }
                
                double currentbalance = senderClient.getAmount();//sender's previous account balance
                double newbalance = senderClient.getAmount()-transfer.getAmount();//sender's current account balance
                double currenttBalance = receiverClient.getAmount();// receiver's previous account balance
                double newwBalance = receiverClient.getAmount()+transfer.getAmount();//receiver's current account balance
                transfer.setStatus("SUCCESS!");

                return String.format("Transfer Successful\n"+
                                    "Sender's Account Holder: %s\n"+
                                    "Receiver's Account Holder:%s\n"+
                                    "Transaction ID: %s\n"+
                                    "Amount Transfered: %.2f\n"+
                                    "Your previous balance was: %.2f\n"+
                                    "Your new balance is: %,2f\n",
                                    senderClient.getName(),
                                    receiverClient.getName(),
                                    transfer.getId(),
                                    transfer.getAmount(),
                                    currentbalance,
                                    newwBalance);
        
        } 
        catch (Exception e) {
            transfer.setStatus("FAILED");
            return "Error: Transfer failed";
        }
    }
}

