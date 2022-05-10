package com.example.tinkofftradingrobot.strategy;

import com.example.tinkofftradingrobot.model.AccountEntity;
import com.example.tinkofftradingrobot.model.UserEntity;
import com.example.tinkofftradingrobot.repository.AccountRepo;
import com.example.tinkofftradingrobot.repository.OrderRepo;
import com.example.tinkofftradingrobot.repository.UserRepo;
import com.example.tinkofftradingrobot.strategy.solution.SolutionMaker;
import com.example.tinkofftradingrobot.strategy.solution.StubSolutionMaker;
import com.example.tinkofftradingrobot.util.Strategy;
import org.apache.catalina.User;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.stream.Collectors;

@Component
public class SolutionInvoker {

    private final OrderRepo orderRepo;
    private final AccountRepo accountRepo;
    private final UserRepo userRepo;

    // пока то что я придумал \_0_0_/, возможно это в памяти хранить не самое оптимальное решение
    // потом если что перепишем на бд
    private List<UserEntity> usersSortedById;
    // can be replaced with concurrent
    private final Integer lockUsers = 0;

    private final Map<Strategy, SolutionMaker> solutionMakers;

    public SolutionInvoker(OrderRepo orderRepo, AccountRepo accountRepo, UserRepo userRepo) {
        this.orderRepo = orderRepo;
        this.accountRepo = accountRepo;
        this.userRepo = userRepo;

        this.usersSortedById = loadUsersFromRepo();

        solutionMakers = new HashMap<>();
        solutionMakers.put(Strategy.STUB, new StubSolutionMaker());
    }

    private List<UserEntity> loadUsersFromRepo() {
        return new ArrayList<>(userRepo.findAllSortedById());
    }
    // method to update users from database to work with new elements
    // complexity: O(n) - if im'ma sure, db sorts elements by default else O(n * log n)
    public void updateActiveAccounts() {
        var userEntities = new ArrayList<UserEntity>();
        var usersSorted = userRepo.findAllSortedById();
        synchronized (lockUsers) {
            // check whether users were changed (account change)
            var it1 = usersSorted.listIterator();
            var it2 = usersSortedById.listIterator();

            UserEntity u1; UserEntity u2;
            while (it1.hasNext() || it2.hasNext()) {
                if (!it1.hasNext()) {
                    userEntities.add(it2.next());
                } else if (!it2.hasNext()) {
                    userEntities.add(it1.next());
                } else {
                    u1 = it1.next();
                    u2 = it2.next();
                    if (u1.getId() < u2.getId()) {
                        userEntities.add(u1);
                        it2.previous();
                    } if (u1.getId() > u2.getId()) {
                        userEntities.add(u2);
                        it1.previous();
                    } else {
                        userEntities.add(u2);
                    }
                }
            }
            usersSortedById = userEntities;
        }
    }

    public void run() {
        // здесь будет делаться вся работа
        synchronized (lockUsers) {

        }

    }


    private void buy() {

    }

    private void sell() {

    }
}
