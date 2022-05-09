package com.example.tinkofftradingrobot.repository;

import com.example.tinkofftradingrobot.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<UserEntity, Long> {

    boolean existsUserEntitiesByTokenEquals(String token);

    Optional<UserEntity> findByToken(String token);
}
