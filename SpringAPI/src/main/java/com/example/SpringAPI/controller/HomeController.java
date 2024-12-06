package com.example.SpringAPI.controller;

import com.example.SpringAPI.entity.Client;
import com.example.SpringAPI.entity.Transaction;
import com.example.SpringAPI.repository.ClientRepository;
import com.example.SpringAPI.repository.TransactionRepository;
import com.example.SpringAPI.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    ClientService clientService;

    @Autowired
    TransactionRepository transactionRepository;


    @GetMapping("/authentication")
    public String authenticationPage(){
        return "authentication";
    }

    @GetMapping("/greeting/{email}")
    public String homePage(@PathVariable("email") String email, Model model){
        Client client = clientRepository.findByEmail(email);
        model.addAttribute("id",client.getId());
        model.addAttribute("username",client.getFirstName());
        model.addAttribute("client",client);
        model.addAttribute("email",client.getEmail());
        if(Objects.equals(client.getStatus(), "logged in"))
            return "home";
        else
            return "authentication";
    }

    @GetMapping("/transactions/{email}")
    public String transactionsPage(@PathVariable("email") String email, Model model){
        Client client = clientRepository.findByEmail(email);
        model.addAttribute("email",email);
        model.addAttribute("id",client.getId());
        model.addAttribute("client",client);
        return "transactions";
    }

    @GetMapping("/registration")
    public String registrationPage(){
        return "registration";
    }

    @PostMapping("/logout/{email}")
    public String logoutClient(@PathVariable("email") String email) {
        clientService.updateClientStatus(email, "logged out");
        return "redirect:/authentication";
    }




}
