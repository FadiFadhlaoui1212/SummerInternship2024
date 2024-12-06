package com.example.SpringAPI.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter

@Entity
@Table
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long account_number;


    @ManyToOne
    private Client client;

    @Column
    private Double account_balance;

    @Column
    private Date opening_date;

    @Column
    private String currency;
    

    public Account(){
        // TODO Auto-generated constructor stub
    }

    public Account(Client client, Double account_balance){
        this.client = client;
        this.account_balance = account_balance;
    }


    @Override
    public String toString() {
        return super.toString();
    }


}
