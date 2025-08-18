package com.example.demo.service;
import java.util.Scanner;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.ClientRepository;
import com.example.demo.dao.TransactionRespository;
import com.example.demo.model.Client;
import com.example.demo.model.Transaction;

@Service
public class BankingService {

    private ClientRepository clientRepository;
    private TransactionRespository transactionRepository;

    @Autowired
    public BankingService(ClientRepository clientRepository){
        this.clientRepository = clientRepository;
    }

    @Autowired
    public void bankingService(TransactionRespository transactionRepository){
        this.transactionRepository = transactionRepository;
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
                menuResponse="--Register--";
                break;
            case 1:
                menuResponse="--Deposit--";
                break;
            case 2:
                menuResponse="--Withdraw--";
                break;
            case 3:
                menuResponse="--Tranfer--";
                break;
            case 4:
                menuResponse="--Check Balance--";
                break;
            case 5:
                menuResponse="--Reset Pin--";
                break;
            case 6:
                menuResponse="--Mini Statements--";
                break;
            default:
                menuResponse="Input a valid option";
        }
        return menuResponse;
        
        }

        public String handleDeposits(Transaction transaction){
        Scanner scanner= new Scanner(System.in);
        UUID uuid = UUID.randomUUID();
        System.out.print("Enter account number: ");
        String accNumber = scanner.nextLine();

        System.out.print("Enter amount to deposit: ");
        double cashd = scanner.nextDouble();

        System.out.print("Enter PIN: ");
        String confpin = scanner.nextLine();
        scanner.nextLine(); // consume newline

        transaction.setAccountNumber(accNumber);
        transaction.setAmount(cashd);
        transaction.setPin(confpin);
        transaction.setId(uuid.toString());

        return processDeposit(transaction);
        }

        public String processDeposit(Transaction transaction) { 
            try {
                Client optionalClient = clientRepository.findByAccountNumber(transaction.getAccountNumber());
                // System.out.println("The account number is: "+optionalClient.getAccountNumber());
                if (optionalClient==null) {
                    transaction.setStatus("FAILED");
                    return "Error: Account not found";
                }

                Client targetClient = optionalClient;

                
                System.out.println("==Deposit==");

                //Step2: Get the amount;
                 if (transaction.getAmount() <= 0) {
                    transaction.setStatus("FAILED");
                    return "Error: Amount must be greater than 0";
                }

                //input pin
                if (!targetClient.getPin().equals((transaction.getPin()))) {
                    transaction.setStatus("FAILED");
                    return "Error: Invalid PIN";
                }
                
                //carrying out transaction
                double currentBalance = targetClient.getAmount();
                double newBalance = currentBalance + transaction.getAmount();
                targetClient.setAmount(newBalance);
                transaction.setStatus("SUCCESS");
                clientRepository.save(targetClient);
                transactionRepository.save(transaction);

    
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
        String confpin = scanner.nextLine();
        scanner.nextLine(); // consume newline

        transaction.setAccountNumber(accNumber);
        transaction.setAmount(cashw);
        transaction.setPin(confpin);
        transaction.setId(UUID.randomUUID().toString());

        return processWithdraw(transaction);
        }

        public String processWithdraw(Transaction transactions){
            
            try {
                Client optionalClient = clientRepository.findByAccountNumber(transactions.getAccountNumber());
                if (optionalClient==null) {
                    transactions.setStatus("FAILED");
                    return "Error: Account not found";
                }

                Client targetClient = optionalClient;            
                System.out.println("==Withdraw==");

                //entering the amount to withdraw
                if(targetClient.getAmount()< transactions.getAmount()){
                    transactions.setStatus("FAILED");
                    return "Not enough funds to carry out transaction";
                }

                //validating the pin
                if(!targetClient.getPin().equals(transactions.getPin())){
                    transactions.setStatus("FAILED");
                    return "Incorrect Pin";
                }
                

               //carrying out transaction
                double accBalance = targetClient.getAmount();//previous account balance
                double newBalance = targetClient.getAmount() - transactions.getAmount();
                double accNewBalance = newBalance;
                targetClient.setAmount(accNewBalance);//current account balance
                // transaction.setAmount(newBalance);//current user balance
                transactions.setStatus("SUCCESS");
                transactionRepository.save(transactions);

                return String.format("Withdraw Successful! You have withdrawn "+transactions.getAmount()+" Your account balance is "+accNewBalance);
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
                transactions.setStatus("FAILED");
                return "Error: Withdraw failed - ";
            }
        
        }

        public String processTransfer(Transaction transfer){
                try {

                Client optionalSender = clientRepository.findByAccountNumber(transfer.getSenderAccountNumber());
                if (optionalSender==null) {
                    transfer.setStatus("FAILED");
                    return "Error: Account not found";
                }

                Client optionalReceiver = clientRepository.findByAccountNumber(transfer.getReceiverAccountNumber());
                if (optionalReceiver==null) {
                    transfer.setStatus("FAILED");
                    return "Error: Account not found";
                }

            Client senderClient = optionalSender;
            Client receiverClient = optionalReceiver;

                if(!senderClient.getPin().equals(transfer.getPin())){
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
                transactionRepository.save(transfer);

                return String.format("Transfer Successful!\n"+
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
                                    newbalance);
                                
        
        } 
        
        catch (Exception e) {
            transfer.setStatus("FAILED");
            return "Error: Transfer failed";
        }

        
    }

    public String processRegistration(Client clients){

        if (clientRepository.existsByAccountNumber(clients.getAccountNumber())) {
            return "Account with this number already exists!";
        }
        clientRepository.save(clients);
        return "Clients registered successfully!";
    

    }
}

