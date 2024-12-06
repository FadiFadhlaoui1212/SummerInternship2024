package com.example.SpringAPI.service.impl;

import com.example.SpringAPI.dto.TransactionDto;
import com.example.SpringAPI.entity.Transaction;
import com.example.SpringAPI.exception.ResourceNotFoundException;
import com.example.SpringAPI.mapper.ClientMapper;
import com.example.SpringAPI.mapper.TransactionMapper;
import com.example.SpringAPI.repository.TransactionRepository;
import com.example.SpringAPI.service.TransactionService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    TransactionRepository transactionRepository;

    @Override
    public TransactionDto createTransaction(TransactionDto transactionDto){
        Transaction transaction = TransactionMapper.mapToTransaction(transactionDto);
        Transaction savedTransaction = transactionRepository.save(transaction);
        return  TransactionMapper.mapToTransactionDto(savedTransaction);
    }

    @Override
    public List<TransactionDto> getAllTransactions(){
        List<Transaction> transactions = transactionRepository.findAll();
        return transactions.stream().map((transaction) -> TransactionMapper.mapToTransactionDto(transaction))
                .collect(Collectors.toUnmodifiableList());
    }

    @Override
    public void deleteTransaction(Long transactionId){
        Transaction transaction = transactionRepository.findById(transactionId).orElseThrow(
                () -> new ResourceNotFoundException("Transaction is not found with the given id"+ transactionId)
        );
        transactionRepository.deleteById(transactionId);
    }
}
