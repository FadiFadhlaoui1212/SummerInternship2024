package com.example.SpringAPI.mapper;

import com.example.SpringAPI.dto.ClientDto;
import com.example.SpringAPI.dto.TransactionDto;
import com.example.SpringAPI.entity.Client;
import com.example.SpringAPI.entity.Transaction;

public class TransactionMapper {

    public static TransactionDto mapToTransactionDto(Transaction transaction){
        return new TransactionDto(
                transaction.getTransaction_id(),
                transaction.getAccount(),
                transaction.getType(),
                transaction.getTransaction_date(),
                transaction.getAmount()
        );
    }

    public static Transaction mapToTransaction(TransactionDto transactionDto){
        return new Transaction(
                transactionDto.getTransaction_id(),
                transactionDto.getAccount(),
                transactionDto.getType(),
                transactionDto.getTransaction_date(),
                transactionDto.getAmount()
        );
    }
}
