package com.example.tinkofftradingrobot.repository;

import com.example.tinkofftradingrobot.model.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Date;
import java.util.List;

public interface OrderRepo extends JpaRepository<OrderEntity, Long> {

    @Query("select a from OrderEntity a where a.date <= :date")
    List<OrderEntity> findAllWithDateTimeBefore(@Param("date")Date date);

}

