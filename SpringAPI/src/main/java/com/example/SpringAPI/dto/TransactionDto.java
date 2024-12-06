package com.example.SpringAPI.dto;

import com.example.SpringAPI.entity.Account;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDto {

    private Long transaction_id;
    private Account account;
    private String type;
    private Date transaction_date;
    private Double amount;
}
