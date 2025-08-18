package com.example.demo.helper;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.dao.ClientRepository;
import com.example.demo.model.Client;
public class Constants {

@Autowired
private ClientRepository clientRepository;

List<Client> customers = clientRepository.findAll();

}