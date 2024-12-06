package com.example.SpringAPI.mapper;

import com.example.SpringAPI.dto.ClientDto;
import com.example.SpringAPI.entity.Client;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;


public class ClientMapper {

    public static ClientDto mapToClientDto(Client client){
        return new ClientDto(
                client.getId(),
                client.getFirstName(),
                client.getLastName(),
                client.getEmail(),
                client.getPassword(),
                client.getBirthDate(),
                client.getLivingPlace(),
                client.getBirthPlace(),
                client.getStatus()

        );
    }

    public static Client mapToClient(ClientDto clientDto){
        return new Client(
                clientDto.getId(),
                clientDto.getFirstName(),
                clientDto.getLastName(),
                clientDto.getEmail(),
                clientDto.getPassword(),
                clientDto.getBirthDate(),
                clientDto.getLivingPlace(),
                clientDto.getBirthPlace(),
                clientDto.getStatus()
        );
    }
}
