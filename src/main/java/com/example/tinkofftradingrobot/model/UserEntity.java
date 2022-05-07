package com.example.tinkofftradingrobot.model;

import com.example.tinkofftradingrobot.strategy.Strategy;
import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@Builder
@Getter
@Setter
@Entity
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity that = (UserEntity) o;
        return Objects.equals(userId, that.userId) && Objects.equals(accountId, that.accountId)
                && strategy == that.strategy && Objects.equals(orders, that.orders);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, accountId, strategy, orders);
    }
}
