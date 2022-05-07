package com.example.tinkofftradingrobot.model;

import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.CreationTimestamp;
import ru.tinkoff.piapi.contract.v1.OrderDirection;
import ru.tinkoff.piapi.contract.v1.OrderType;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
@Entity
@Table(name = "orders")
public class OrderEntity {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long orderId;

    @Column(name = "figi")
    private String figi;

    @Column(name = "price")
    private Long price;

    // owner of relationship - order
    @ManyToOne()
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    private AccountEntity account;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "direction")
    @Enumerated(EnumType.STRING)
    private OrderDirection direction;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private OrderType type;

    @Column(name="timestamp", nullable = false, updatable = false, insertable = false)
    @CreationTimestamp
    private Timestamp timestamp;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        OrderEntity that = (OrderEntity) o;
        return Objects.equals(orderId, that.orderId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
