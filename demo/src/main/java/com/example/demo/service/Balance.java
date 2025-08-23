package com.example.demo.service;

// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Client;

@Service 
public class Balance {

    // @Autowired
    // private ClientRepository clientRepository;

    // public String processBalance(Client getBalance){
    //     Scanner scanner= new Scanner(System.in);
    //     System.out.println("Enter your account number");
    //     String accNumber= scanner.nextLine();

    //     System.out.println("Enter Your pin");
    //     String confpin = scanner.nextLine();

    //     getBalance.setAccountNumber(accNumber);
    //     getBalance.setPin(confpin);

    //     return findBalance(getBalance);    
    // }

    public String findBalance(Client balancee){
//         // System.out.println("Account Number: "+balancee.getAccountNumber()+" Pin: "+balancee.getPin());
// System.out.println("Client name === "+balancee.getName());
//         Client client = clientRepository.findByAccountNumber(balancee.getAccountNumber());
                
//                 if (client==null) {
//                     balancee.setStatus("FAILED");
//                     return "Invalid Account Number or Pin";
//                 }
//                  Client targetClient = client;
                return "Your balance is " +balancee.getName();                
    }
}

