package com.example.tinkofftradingrobot.model;


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
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name="token", unique = true)
    private String token;

    @OneToMany(mappedBy = "user")
    private List<AccountEntity> accountEntityList;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity that = (UserEntity) o;
        return Objects.equals(token, that.token);
    }

    @Override
    public int hashCode() {
        return Objects.hash(token);
    }
}