package com.example.tinkofftradingrobot.model;

import com.example.tinkofftradingrobot.strategy.Strategy;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@RequiredArgsConstructor
@Getter
@Setter
@Table(name= "users")
public class UserEntity {
    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "account_id")
    private String accountId;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "strategy")
    private Strategy strategy;

    // orders - owner of relationship
    @OneToMany(mappedBy = "user")
    private List<OrderEntity> orders;
}
