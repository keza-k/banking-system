package com.example.demo.api;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.ClientRepository;
import com.example.demo.model.Client;
import com.example.demo.model.MyUsers;
import com.example.demo.model.Transaction;
import com.example.demo.service.BankingService;

@RestController
@CrossOrigin(origins= "http://localhost:8000")
@RequestMapping("/banking")
public class Controllers {
    @GetMapping("/havoc/{path:[^\\.]*}")
    public String forward()
    {
        return  "forward:/";
    }

    @Autowired
    private BankingService bankingService;

    
    // @Autowired(required=false)
    // public Controllers(Balance balance){
    //     this.balance = balance;
    // }


    @Autowired
    private ClientRepository clientRepository;

    // @Autowired 
    // private  TransactionRespository transactionRepository;

    // @Autowired
    // private MyUsers myUsers;

    // @Autowired
    // private MyUserRepository myUserRepository;

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
    public String checkBalance(@RequestBody Client balancee){
        return bankingService.findBalance(balancee);
    }

    @PostMapping("/addClients")
        public String  processRegistration(@RequestBody Client client){
            return bankingService.processRegistration(client);
        }

    @PostMapping("/Credentials")
        public Map<String,Object> loginProcess(@RequestBody MyUsers users){
            return bankingService.Users(users);
        }


    @GetMapping("/miniStatements")
    public List<Transaction> miniStatements(@RequestBody Map<String, String > request){
        String accountNumber = request.get("accountNumber");
        String pin = request.get("pin");

        return bankingService.miniStatements(accountNumber, pin);
    }
    }