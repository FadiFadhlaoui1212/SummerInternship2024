package com.example.SpringAPI.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginDto {

    private String email;
    private String password;

    public LoginDto(String email, String password){
        this.email = email;
        this.password = password;
    }

    public LoginDto(){

    }
}
