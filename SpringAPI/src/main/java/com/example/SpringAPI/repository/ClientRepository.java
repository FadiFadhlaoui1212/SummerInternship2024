package com.example.SpringAPI.repository;

import com.example.SpringAPI.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@EnableJpaRepositories
@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    Client findByEmail(String email);
    Optional<Client> findOneByEmailAndPassword(String email, String password);
    Optional<Client> findOneByEmail(String email);
}
