package com.example.SpringAPI.controller;


import com.example.SpringAPI.dto.TransactionDto;
import com.example.SpringAPI.entity.Account;
import com.example.SpringAPI.entity.Transaction;
import com.example.SpringAPI.mapper.TransactionMapper;
import com.example.SpringAPI.service.AccountService;
import com.example.SpringAPI.service.TransactionService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private AccountService accountService;

    @PostMapping("/create")
    public ResponseEntity<TransactionDto> createTransaction(@RequestBody TransactionDto transactionDto){
        TransactionDto savedTransaction = transactionService.createTransaction(transactionDto);
        return new ResponseEntity<>(savedTransaction, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<TransactionDto>> getAllTransactions(){
        List<TransactionDto> transactions = transactionService.getAllTransactions();
        return ResponseEntity.ok(transactions);
    }

    @GetMapping("/{clientId}")
    public ResponseEntity<List<TransactionDto>>  getTransactionsByClientId(@PathVariable("clientId") Long clientId){
        List<TransactionDto> transactionDtos = transactionService.getAllTransactions();
        List<TransactionDto> selectedDtos = new ArrayList<>();
        for (TransactionDto transactionDto:  transactionDtos){
            Transaction transaction = TransactionMapper.mapToTransaction(transactionDto);
            if(transactionDto.getAccount() != null){
                if( transactionDto.getAccount().getClient().getId()==clientId){
                    selectedDtos.add(transactionDto);
                }
            }
        }
        return ResponseEntity.ok(selectedDtos);
    }

    @DeleteMapping("delete/{accountNumber}")
    public ResponseEntity<String> deleteTransactionsByAccountNumber(@PathVariable("accountNumber") Long accountNumber){
        List<TransactionDto> transactionDtos = transactionService.getAllTransactions();
        for (TransactionDto transactionDto: transactionDtos){
            if(transactionDto.getAccount() != null){
                if (transactionDto.getAccount().getAccount_number()==accountNumber) {
                    Long id = transactionDto.getTransaction_id();
                    transactionService.deleteTransaction(id);
                }
            }
        }
        return ResponseEntity.ok("Transactions deleted successfully");
    }
}
