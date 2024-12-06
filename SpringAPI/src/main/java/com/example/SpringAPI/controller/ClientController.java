package com.example.SpringAPI.controller;

import com.example.SpringAPI.dto.ClientDto;
import com.example.SpringAPI.dto.LoginDto;
import com.example.SpringAPI.entity.Client;
import com.example.SpringAPI.response.LoginResponse;
import com.example.SpringAPI.service.ClientService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;



import java.security.Principal;
import java.util.List;

@AllArgsConstructor
@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @Autowired
    private PasswordEncoder passwordEncoder;



    //Build Add Employee REST API
    @PostMapping
    public ResponseEntity<ClientDto> createClient(@RequestBody ClientDto clientDto) {
        clientDto.setPassword(this.passwordEncoder.encode(clientDto.getPassword()));
        ClientDto savedClient = clientService.createClient(clientDto);
        return new ResponseEntity<>(savedClient, HttpStatus.CREATED);
    }

    //Build Get Employee REST API
    @GetMapping("{id}")
    public ResponseEntity<ClientDto> getClientById(@PathVariable("id") Long clientId) {
        ClientDto clientDto = clientService.getClientById(clientId);
        return ResponseEntity.ok(clientDto);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<ClientDto> getClientByEmail(@PathVariable("email") String email){
        ClientDto clientDto = clientService.getClientByEmail(email);
        return ResponseEntity.ok(clientDto);
    }

    //Build Get All Employees REST API
    @GetMapping()
    public ResponseEntity<List<ClientDto>> getAllClients() {
        List<ClientDto> clients = clientService.getAllClients();
        return ResponseEntity.ok(clients);
    }

    //Build Update Employee REST API
    @PutMapping("{id}")
    public ResponseEntity<ClientDto> updateClient(@PathVariable("id") Long clientId,
                                                  @RequestBody ClientDto updatedClient) {
        ClientDto clientDto = clientService.updateClient(clientId, updatedClient);
        return ResponseEntity.ok(clientDto);
    }

    @PutMapping("switchOn/{email}")
    public void switchToLoginStatus(@PathVariable("email") String email){
        clientService.updateClientStatus(email, "logged in");
    }

    @PutMapping("switchOut/{email}")
    public void switchToLogoutStatus(@PathVariable("email") String email) {
        clientService.updateClientStatus(email, "logged out");
    }

    //Build Delete Employee REST API
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteClient(@PathVariable("id") Long clientId) {
        clientService.deleteClient(clientId);
        return ResponseEntity.ok("Employee deleted successfully !");
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginClient(@RequestBody LoginDto loginDto){
        LoginResponse loginResponse = clientService.loginClient(loginDto);
        ClientDto updatedClient = new ClientDto();
        updatedClient.setStatus("logged in");
        return ResponseEntity.ok(loginResponse);
    }







}

