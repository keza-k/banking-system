package com.example.demo.helper;
import java.util.ArrayList;

import com.example.demo.model.Client;
public class Constants {
        public static  ArrayList<Client> createClients(){

                /// dummy client
        ArrayList<Client> ourcustomers = new ArrayList<>();

        ourcustomers.add(new Client("Keza","001","00121",1000,12345));
        ourcustomers.add(new Client("Kayitare","002","00212",0,23456));
        ourcustomers.add(new Client("Trinity","003","00308",10000,34567));
        ourcustomers.add(new Client("Michael","004","00408",10000,45678));
        ourcustomers.add(new Client("Trust","005","00519",0,56789));
        return ourcustomers;       
        }
        
    
}