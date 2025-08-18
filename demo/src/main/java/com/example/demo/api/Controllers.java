package com.example.demo.api;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.ClientRepository;
import com.example.demo.dao.TransactionRespository;
import com.example.demo.model.Client;
import com.example.demo.model.Transaction;
import com.example.demo.service.BankingService;

@RestController
@RequestMapping("/banking")
public class Controllers {

    @Autowired
    private BankingService bankingService;

    
    // @Autowired(required=false)
    // public Controllers(Balance balance){
    //     this.balance = balance;
    // }

    @Autowired
    private ClientRepository clientRepository;

    @Autowired 
    private  TransactionRespository transactionRepository;

    @GetMapping("/getAllCustomer")
    public List<Client> getAllClients(){
        return clientRepository.findAll();
    
    }
    @GetMapping("/gettheMenu")
    public String placeTheMenu(){
        return BankingService.bankingmenu();
    }

    @GetMapping("/menuSelect")
    public String menuInteraction(@RequestParam Integer choices) {
        return bankingService.respondMenu(choices);
    }

    @PostMapping("/deposit")
    public String processDeposit(@RequestBody Transaction transaction) {
        return bankingService.processDeposit(transaction);
    }
    @PostMapping("/Withdraw")
    public String processWithdraw(@RequestBody Transaction transaction) {
        return bankingService.processWithdraw(transaction);
    }
    @PostMapping("/Transfer")
    public String processTransfer(@RequestBody Transaction transaction) {
        return bankingService.processTransfer(transaction);
    }
    @PostMapping("/findBalance")
    public String checkBalance(@RequestBody Transaction balancee){
        System.out.println("Request: "+balancee.getAccountNumber()+" PIN: "+balancee.getPin());
        Client client = clientRepository.findByAccountNumber(balancee.getAccountNumber());
        // Client Client = clientRepository.findByPin(balancee.getPin()).orElseThrow(() -> new RuntimeException("Client not found"));
        // Client targetClient = client.get();
        balancee.setPin(balancee.getPin());
        // System.out.println("Client: "+targetClient.getAmount());
        // System.out.println("Client1: "+Client.getAmount());
        
        return null;
    }

    @PostMapping("/addClients")
        public String  processRegistration(@RequestBody Client client){
            return bankingService.processRegistration(client);
        }
    }