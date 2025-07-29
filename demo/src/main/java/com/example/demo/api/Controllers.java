package com.example.demo.api;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.helper.Constants;  
import com.example.demo.model.Client;  

@RestController
@RequestMapping("/banking")
public class Controllers {


    public List<Client> customers= new ArrayList<>();

    @GetMapping("/getAllCustomer")
    public ArrayList<Client> getAllClients(){
        return Constants.createClients();
    }
}
