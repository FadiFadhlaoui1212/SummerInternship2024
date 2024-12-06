package com.example.SpringAPI.service.impl;

import com.example.SpringAPI.dto.ClientDto;
import com.example.SpringAPI.dto.LoginDto;
import com.example.SpringAPI.entity.Client;
import com.example.SpringAPI.exception.ResourceNotFoundException;
import com.example.SpringAPI.mapper.ClientMapper;
import com.example.SpringAPI.repository.ClientRepository;
import com.example.SpringAPI.response.LoginMessage;
import com.example.SpringAPI.response.LoginResponse;
import com.example.SpringAPI.service.ClientService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ClientServiceImpl implements ClientService {


    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public ClientDto createClient(ClientDto clientDto) {
        Client client = ClientMapper.mapToClient(clientDto);
        Client savedClient = clientRepository.save(client);
        return ClientMapper.mapToClientDto(savedClient);
    }

    @Override
    public ClientDto getClientById(Long employeeId) {
        Client client = clientRepository.findById(employeeId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Employee is not found with given id : " + employeeId));

        return ClientMapper.mapToClientDto(client);
    }

    @Override
    public ClientDto getClientByEmail(String email) {
        Client client = clientRepository.findByEmail(email);
        return ClientMapper.mapToClientDto(client);
    }

    @Override
    public List<ClientDto> getAllClients() {
        List<Client> clients = clientRepository.findAll();
        return clients.stream().map((client) -> ClientMapper.mapToClientDto(client))
                .collect(Collectors.toUnmodifiableList());

    }

    @Override
    public ClientDto updateClient(Long clientId, ClientDto updatedClient) {
        Client client = clientRepository.findById(clientId).orElseThrow(
                () -> new ResourceNotFoundException("Employee is not found with given id: " + clientId)
        );

        client.setFirstName(updatedClient.getFirstName());
        client.setLastName(updatedClient.getLastName());
        client.setEmail(updatedClient.getEmail());
        client.setBirthDate(updatedClient.getBirthDate());
        client.setLivingPlace(updatedClient.getLivingPlace());
        client.setBirthPlace(updatedClient.getBirthPlace());

        Client updatedClientObj = clientRepository.save(client);
        return ClientMapper.mapToClientDto(updatedClientObj);
    }

    @Override
    public void deleteClient(Long clientId) {

        Client client = clientRepository.findById(clientId).orElseThrow(
                () -> new ResourceNotFoundException("Employee is not found with given id: " + clientId)
        );

        clientRepository.deleteById(clientId);

    }

    @Override
    public LoginResponse loginClient(LoginDto loginDto) {
        String msg = "";
        Client client = clientRepository.findByEmail(loginDto.getEmail());
        if (client != null) {
            String password1 = loginDto.getPassword();
            String password2 = client.getPassword();
            if (this.passwordEncoder.matches(password1,password2)) {
                Optional<Client> client1 = clientRepository.findOneByEmail(loginDto.getEmail());
                if (client1.isPresent()) {
                    Client foundClient = clientRepository.findByEmail(loginDto.getEmail());
                    foundClient.setStatus("logged in");
                    foundClient = clientRepository.save(foundClient);
                    return new LoginResponse("Login success", true);
                } else {
                    return new LoginResponse("Login Failed", true);
                }
            } else {
                return new LoginResponse("password Not Match", false);
            }
        } else {
            return new LoginResponse("Email doesn't exist", false);
        }

    }

    @Override
    public ClientDto switchToLoginStatus(String email, ClientDto updatedClient){
        Client client = clientRepository.findByEmail(email);

        client.setFirstName(updatedClient.getFirstName());
        client.setLastName(updatedClient.getLastName());
        client.setEmail(updatedClient.getEmail());
        client.setBirthDate(updatedClient.getBirthDate());
        client.setLivingPlace(updatedClient.getLivingPlace());
        client.setBirthPlace(updatedClient.getBirthPlace());
        client.setStatus("logged in");

        Client updatedClientObj = clientRepository.save(client);
        return ClientMapper.mapToClientDto(updatedClientObj);

    }

    @Override
    public ClientDto switchToLogoutStatus(String email, ClientDto updatedClient){

        Client client = clientRepository.findByEmail(email);

        client.setFirstName(updatedClient.getFirstName());
        client.setLastName(updatedClient.getLastName());
        client.setEmail(updatedClient.getEmail());
        client.setBirthDate(updatedClient.getBirthDate());
        client.setLivingPlace(updatedClient.getLivingPlace());
        client.setBirthPlace(updatedClient.getBirthPlace());
        client.setStatus("logged out");

        Client updatedClientObj = clientRepository.save(client);
        return ClientMapper.mapToClientDto(updatedClientObj);
    }

    @Override
    public void updateClientStatus(String email, String status) {
        Client client = clientRepository.findByEmail(email);
        if (client != null) {
            client.setStatus(status);
            clientRepository.save(client);
        }
    }
}
