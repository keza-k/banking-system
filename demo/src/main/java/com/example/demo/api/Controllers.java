package com.example.demo.api;
import java.util.ArrayList;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.helper.Constants;
import com.example.demo.model.Client;
import com.example.demo.model.Transaction;
import com.example.demo.service.Service;

@RestController
@RequestMapping("/banking")
public class Controllers {


    @GetMapping("/getAllCustomer")
    public ArrayList<Client> getAllClients(){
        return Constants.createClients();
    
    }
    @GetMapping("/gettheMenu")
    public String placeTheMenu(){
        return Service.bankingmenu();
    }

    @GetMapping("/menuSelect")
    public String menuInteraction(@RequestParam(required = false) Integer choices) {
        return Service.respondMenu(choices);
    }

    @PostMapping("/deposit")
    public String processDeposit(@RequestBody Transaction transaction) {
        return Service.processDeposit(transaction);
    }
    @PostMapping("/Withdraw")
    public String processWithdraw(@RequestBody Transaction transaction) {
        return Service.processWithdraw(transaction);
    }
    @PostMapping("/Transfer")
    public String processTransfer(@RequestBody Transaction transaction) {
        return Service.processTransfer(transaction);
    }
    
}
