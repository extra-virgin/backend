package com.example.tinkofftradingrobot.repository;

import com.example.tinkofftradingrobot.model.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepo extends JpaRepository<OrderEntity, Long> {

}
