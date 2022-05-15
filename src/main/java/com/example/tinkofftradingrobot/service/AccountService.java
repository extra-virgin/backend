package com.example.tinkofftradingrobot.service;

import com.example.tinkofftradingrobot.dto.AccountDTO;
import com.example.tinkofftradingrobot.repository.AccountRepo;
import com.example.tinkofftradingrobot.repository.UserRepo;
import com.example.tinkofftradingrobot.service.converter.AccountConverter;
import com.example.tinkofftradingrobot.strategy.connection.ConnectionHandler;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import ru.tinkoff.piapi.contract.v1.Account;
import ru.tinkoff.piapi.core.InvestApi;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AccountService {

    private final AccountRepo accountRepo;
    private final UserRepo userRepo;
    private final ConnectionHandler connectionHandler;

    public AccountService(AccountRepo accountRepo, UserRepo userRepo, ConnectionHandler connectionHandler) {
        this.accountRepo = accountRepo;
        this.userRepo = userRepo;
        this.connectionHandler = connectionHandler;
    }

    public Optional<AccountDTO> createAccount(String token, AccountDTO accountDTO) {
        var userOpt = userRepo.findByToken(token);
        if (userOpt.isEmpty()) return Optional.empty();

        var user = userOpt.get();
        if (!isAccountPresent(user.getToken(), user.getIsSandbox(), accountDTO)) {
            return Optional.empty();
        }
        var account = AccountConverter.toEntity(accountDTO);
        account.setUser(user);
        try {
            accountRepo.save(account);
            return Optional.of(AccountConverter.toDTO(account));
        } catch (
                DataIntegrityViolationException e) {
            return Optional.empty();
        }
    }

    @Nullable
    public List<AccountDTO> getAccounts(String token) {
        var userOpt = userRepo.findByToken(token);
        if (userOpt.isEmpty()) return null;
        else {
            var user = userOpt.get();
            return user.getAccountEntityList().stream()
                    .map(AccountConverter::toDTO).collect(Collectors.toList());
        }
    }

    public List<AccountDTO> getTinkoffAccounts(String token) {
        var userOpt = userRepo.findByToken(token);
        if (userOpt.isEmpty()) return null;

        var user = userOpt.get();
        var accounts = getApiAccounts(user.getToken(), user.getIsSandbox());
        if (accounts == null) return null;
        return accounts.stream()
                .map(ac -> AccountDTO.builder().accountId(ac.getId()).build())
                .collect(Collectors.toList());
    }

    public Optional<AccountDTO> updateAccount(String token, AccountDTO accountDTO) {
        var accountOpt = accountRepo.findAccountEntityByAccountId(accountDTO.getAccountId());
        if (accountOpt.isEmpty()) {
            return Optional.empty();
        }
        var account = accountOpt.get();
        if (!account.getUser().getToken().equals(token)) {
            return Optional.empty();
        }
        // updating account
        account.setStrategy(accountDTO.getStrategy());
        accountRepo.save(account);

        return Optional.of(AccountConverter.toDTO(account));
    }

    public Optional<AccountDTO> removeAccount(String token, String accountId) {
        var accountOpt = accountRepo.findAccountEntityByAccountId(accountId);
        if (accountOpt.isEmpty()) {
            return Optional.empty();
        }
        var account = accountOpt.get();
        if (!account.getUser().getToken().equals(token)) {
            return Optional.empty();
        }
        // removing account
        accountRepo.delete(account);
        return Optional.of(AccountConverter.toDTO(account));
    }


    private boolean isAccountPresent(String token, boolean isSandbox, AccountDTO accountDTO) {
        List<Account> accounts = getApiAccounts(token, isSandbox);
        if (accounts == null) {
            return false;
        }
        return accounts.stream().anyMatch(account -> account.getId().equals(accountDTO.getAccountId()));
    }

    @Nullable
    private List<Account> getApiAccounts(String token, boolean isSandbox) {
        InvestApi connection;
        if (isSandbox) {
            connection = connectionHandler.getConnectionSandbox(token);
        } else {
            connection = connectionHandler.getConnection(token);
        }
        if (connection == null) {
            return null;
        }

        List<Account> accounts;
        if (isSandbox) {
            accounts = connection.getSandboxService().getAccountsSync();
        } else {
            accounts = connection.getUserService().getAccountsSync();
        }
        return accounts;
    }
}
