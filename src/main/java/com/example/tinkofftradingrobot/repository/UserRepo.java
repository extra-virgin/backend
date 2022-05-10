package com.example.tinkofftradingrobot.repository;

import com.example.tinkofftradingrobot.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepo extends JpaRepository<UserEntity, Long> {

    boolean existsUserEntitiesByTokenEquals(String token);

    Optional<UserEntity> findByToken(String token);

    // TODO: посмотреть, вообще надо ли доставать все поля
    @Query("select a from UserEntity a order by a.id")
    List<UserEntity> findAllSortedById();
}
