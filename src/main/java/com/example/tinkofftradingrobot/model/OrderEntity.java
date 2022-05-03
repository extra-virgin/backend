package com.example.tinkofftradingrobot.model;

import lombok.*;
import org.hibernate.Hibernate;
import ru.tinkoff.piapi.contract.v1.OrderDirection;
import ru.tinkoff.piapi.contract.v1.OrderType;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "orders")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
public class OrderEntity {

    @Id
    @Column(name = "order_id")
    private String orderId;

    @Column(name = "figi")
    String figi;

    @Column(name = "price")
    Long price;

    @Column(name = "account_id")
    String accountID;

    @Column(name = "quantity")
    int quantity;

    @Column(name = "direction")
    @Enumerated(EnumType.STRING)
    OrderDirection direction;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    OrderType type;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        OrderEntity that = (OrderEntity) o;
        return orderId != null && Objects.equals(orderId, that.orderId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
