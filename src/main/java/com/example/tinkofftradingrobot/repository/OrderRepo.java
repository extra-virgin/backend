package com.example.tinkofftradingrobot.repository;

import com.example.tinkofftradingrobot.model.AccountEntity;
import com.example.tinkofftradingrobot.model.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Date;
import java.util.List;

// TODO если что вдруг можно создать отдельно новый репозиторий, заинжектить entityManager и потом это все под один интерфейс запихать, будет красиво работать и ничем не ограничено
public interface OrderRepo extends JpaRepository<OrderEntity, Long> {

//    @Query("select a from OrderEntity a where a.date <= :date")
    List<OrderEntity> findAllByAccountAndDateBefore(AccountEntity accountEntity, Date date);

    List<OrderEntity> getAllByAccount(AccountEntity accountEntity);

    List<OrderEntity> findAllByAccountOrderByOrderIdDesc(AccountEntity account);
}

