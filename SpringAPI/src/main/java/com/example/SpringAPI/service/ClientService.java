package com.example.SpringAPI.service;

import com.example.SpringAPI.dto.ClientDto;
import com.example.SpringAPI.dto.LoginDto;
import com.example.SpringAPI.entity.Client;
import com.example.SpringAPI.repository.ClientRepository;
import com.example.SpringAPI.response.LoginMessage;
import com.example.SpringAPI.response.LoginResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ClientService {


    ClientDto createClient(ClientDto clientDto);

    ClientDto getClientById(Long clientId);

    ClientDto getClientByEmail(String email);

    List<ClientDto> getAllClients();

    ClientDto updateClient(Long clientId, ClientDto updatedClients);

    void deleteClient(Long clientId);

    LoginResponse loginClient(LoginDto loginDto);

    ClientDto switchToLoginStatus(String email, ClientDto updatedClient);

    ClientDto switchToLogoutStatus(String email, ClientDto updatedClient);

    public void updateClientStatus(String email, String status);




}
