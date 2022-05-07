package com.example.tinkofftradingrobot.repository;

import com.example.tinkofftradingrobot.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<UserEntity, Long> {

}
