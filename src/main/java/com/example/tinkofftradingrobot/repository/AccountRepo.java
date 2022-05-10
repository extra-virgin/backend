package com.example.tinkofftradingrobot.repository;


import com.example.tinkofftradingrobot.model.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepo extends JpaRepository<AccountEntity, Long> {


}
