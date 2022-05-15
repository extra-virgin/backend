package com.example.tinkofftradingrobot.model;

import com.example.tinkofftradingrobot.strategy.Strategy;
import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "accounts")
public class AccountEntity {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "account_id", unique = true, nullable = false)
    private String accountId;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "strategy")
    private Strategy strategy;

    // orders - owner of relationship
    @OneToMany(mappedBy = "account")
    private List<OrderEntity> orders;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserEntity user;

    @ElementCollection
    @CollectionTable(name = "figis", joinColumns = @JoinColumn(name = "id"))
    @Column(name = "figi")
    private List<String> figis;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountEntity that = (AccountEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, accountId, strategy, orders);
    }
}
