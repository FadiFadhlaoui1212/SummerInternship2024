package com.example.SpringAPI.controller;

import com.example.SpringAPI.entity.Account;
import com.example.SpringAPI.service.AccountService;
import com.example.SpringAPI.service.ClientService;
import com.example.SpringAPI.service.TransactionService;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    AccountService accountService;
    ClientService clientService;



    @PostMapping("/create")
    public ResponseEntity<Account> createAccount(@RequestBody Account account) {
        Account createAccount = accountService.createAccount(account);
        return ResponseEntity.status(HttpStatus.CREATED).body(createAccount);
    }

    @GetMapping("/{accountNumber}")
    public Account getAccountByAccountNumber(@PathVariable Long accountNumber){
        Account account = accountService.getAccountDetailsByAccountNumber(accountNumber);
        return account;
    }

    @GetMapping("/getallaccounts")
    public List<Account> getAllAccountDetails(){
        List<Account> allAccountDetails = accountService.getAllAccountDetails();
        return allAccountDetails;
    }

    @PutMapping("/deposit/{accountNumber}/{amount}")
    public Account depositAccount(@PathVariable Long accountNumber,@PathVariable Double amount){
        Account account = accountService.depositAmount(accountNumber, amount);
        return account;
    }

    @PutMapping("/withdraw/{accountNumber}/{amount}")
    public Account withdrawAccount(@PathVariable Long accountNumber,@PathVariable Double amount){
        Account account = accountService.withdrawAmount(accountNumber, amount);
        return account;
    }

    @DeleteMapping("/delete/{accountNumber}")
    public ResponseEntity<String> deleteAccount(@PathVariable Long accountNumber){
        accountService.closeAccount(accountNumber);
        return ResponseEntity.ok("Account closed");
    }

    @GetMapping("/clients/{clientId}")
    public ResponseEntity<List<Account>> getAccountsByClientId(@PathVariable Long clientId){
        List<Account> accounts = accountService.getAccountsByClientId(clientId);
        if (accounts.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(accounts, HttpStatus.OK);
    }

}
