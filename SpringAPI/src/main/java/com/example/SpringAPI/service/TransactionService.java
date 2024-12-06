package com.example.SpringAPI.service;

import com.example.SpringAPI.dto.ClientDto;
import com.example.SpringAPI.dto.TransactionDto;

import java.util.List;

public interface TransactionService {



    TransactionDto createTransaction(TransactionDto transactionDto);
    List<TransactionDto> getAllTransactions();
    void deleteTransaction(Long transactionId);
}
