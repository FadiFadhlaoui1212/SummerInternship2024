package com.example.SpringAPI.service.impl;

import com.example.SpringAPI.entity.Account;
import com.example.SpringAPI.repository.AccountRepository;
import com.example.SpringAPI.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    AccountRepository repo;


    @Override
    public Account createAccount(Account account){
        Account account_saved = repo.save(account);
        return account_saved;
    }

    @Override
    public Account getAccountDetailsByAccountNumber(Long accountNumber){
        // TODO Auto-generated method sub
        Optional<Account> account = repo.findById(accountNumber);
        if(account.isEmpty()){
            throw new RuntimeException("Account doesn't exist");
        }
        Account found_account = account.get();
        return found_account;
    }

    @Override
    public List<Account> getAllAccountDetails(){
        List<Account> listOfAccounts = repo.findAll();
        return listOfAccounts;
    }

    @Override
    public Account depositAmount(Long accountNumber, Double money){
        Optional<Account> account = repo.findById(accountNumber);
        if(account.isEmpty()){
            throw new RuntimeException("Account is not present");
        }
        Account Presentaccount = account.get();
        Double totalBalance = Presentaccount.getAccount_balance()+money;
        Presentaccount.setAccount_balance(totalBalance);
        repo.save(Presentaccount);
        return Presentaccount;
    }

    @Override
    public Account withdrawAmount(Long accountNumber, Double amount){
        Optional<Account> account = repo.findById(accountNumber);
        if(account.isEmpty()){
            throw new RuntimeException("Account is not present");
        }
        Account Presentaccount = account.get();
        if(Presentaccount.getAccount_balance()<amount){
            throw new RuntimeException("You can't withdraw an amount of money greater than your account's balance");
        }
        Double totalBalance = Presentaccount.getAccount_balance()-amount;
        Presentaccount.setAccount_balance(totalBalance);
        repo.save(Presentaccount);
        return Presentaccount;
    }

    @Override
    public void closeAccount(Long accountNumber){
        getAccountDetailsByAccountNumber(accountNumber);
        repo.deleteById(accountNumber);
        // TODO Auto-generated method sub
    }

    @Override
    public List<Account> getAccountsByClientId(Long clientId){
        return repo.findByClientId(clientId);
    }
}
