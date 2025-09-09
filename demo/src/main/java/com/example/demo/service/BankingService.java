package com.example.demo.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.dao.ClientRepository;
import com.example.demo.dao.TransactionRespository;
import com.example.demo.model.Client;
import com.example.demo.model.MyUsers;
import com.example.demo.model.Transaction;

@Service
public class BankingService {

    private final ClientRepository clientRepository;
    private TransactionRespository transactionRepository;
    //  private MyUserRepository myUserRepository;

    @Autowired
    public BankingService(ClientRepository clientRepository){
        this.clientRepository = clientRepository;
    }

    @Autowired
    public void bankingService(TransactionRespository transactionRepository){
        this.transactionRepository = transactionRepository;
    }

    // @Autowired
    // public void MyUsers(MyUserRepository myUserRepository){
    //     this.myUserRepository = myUserRepository;
    
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    public static String bankingmenu(){
        StringBuilder getMenu=new StringBuilder();
        getMenu.append("*************************\n        K Banking       \n*************************\nWelcome to K Banking System\n0. Register\n1. Deposit\n2. Withdraw\n3. Transfer\n4. Check Balance\n5. Reset Pin\n6. Mini Statement");
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
                double newBalance = accBalance - transactions.getAmount();
                targetClient.setAmount(newBalance);//current account balance
                transactions.setStatus("SUCCESS");
                clientRepository.save(targetClient);
                transactionRepository.save(transactions);


                return String.format("Withdraw Successful! You have withdrawn "+transactions.getAmount()+" Your account balance is "+newBalance);
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
                return "Error: Withdraw failed - "+ e.getMessage();
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
                // double currenttBalance = receiverClient.getAmount();// receiver's previous account balance
                // double newwBalance = receiverClient.getAmount()+transfer.getAmount();//receiver's current account balance
                transfer.setStatus("SUCCESS!");
                transactionRepository.save(transfer);

                return String.format("""
                                     Transfer Successful!
                                     Sender's Account Holder: %s
                                     Receiver's Account Holder:%s
                                     Transaction ID: %s
                                     Amount Transfered: %.2f
                                     Your previous balance was: %.2f
                                     Your new balance is: %,2f
                                     """,
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
            return "Account with this Account Number already exists!";
            
        }

        // String email = ;
        String hashedPin = passwordEncoder.encode(clients.getPin());
        clients.setPin(hashedPin);
        clients.setAccountNumber(clients.randomSequentialVal(6));
        clients.setID(clients.generateSequentialID(4));
        clientRepository.save(clients);
        return "Clients registered successfully!";


    }

    public String findBalance(Client balancee){
        Client client = clientRepository.findByAccountNumber(balancee.getAccountNumber());
                
                if (client==null) {
                    balancee.setStatus("FAILED");
                    return "Invalid Account Number";
                }

                if(!client.getPin().equals(balancee.getPin())){
                    balancee.setStatus("FAILED");
                    return "Incorrect Pin";
                }
                
                Client targetClient = client;
               return "Your balance is " +targetClient.getAmount();                
    }

     public String Users(MyUsers users){        
        Client customer = clientRepository.findByEmail(users.getEmail());
        System.err.println("the customer value is ===== "+customer.getPin());
        // if(customer != null){
        if(!users.getPin().equals(customer.getPin())){
            // users.setStatus("FAILED");
            return "Invalid email and Incorrect pin";
        }
        
             // users.setStatus("FAILED");
            return String.format("Welcome %s!!! Login successful",customer.getName());
    }

    public List<Transaction> miniStatements(String accountNumber, String pin){
        Client client = clientRepository.findByAccountNumber(accountNumber);
        if(client != null){
             if(!passwordEncoder.matches(pin, client.getPin())){
            throw new RuntimeException("Invalid email or pin!") ;
        }
    }        

        List<Transaction> transactions = transactionRepository.findByAccountNumber(accountNumber);

        if(transactions == null){
            throw new RuntimeException("No transactions found for this account.");
        }

        return transactions.stream()
                            .sorted((a,b) -> b.getTimestamp().compareTo(a.getTimestamp()))
                            .limit(3)
                            .toList() ;
    
   
    
    
    
}
}

