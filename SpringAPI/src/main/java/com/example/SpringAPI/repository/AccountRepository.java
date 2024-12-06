package com.example.SpringAPI.repository;

import com.example.SpringAPI.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@EnableJpaRepositories
public interface AccountRepository extends JpaRepository<Account, Long> {
    List<Account> findByClientId(Long ClientId);

}
