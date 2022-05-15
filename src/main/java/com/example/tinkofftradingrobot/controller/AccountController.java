package com.example.tinkofftradingrobot.controller;

import com.example.tinkofftradingrobot.dto.AccountDTO;
import com.example.tinkofftradingrobot.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.example.tinkofftradingrobot.config.security.ApiAuthenticationToken.getApiAuthTokenFromContext;

@RestController
@RequestMapping("/api/account")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createAccount(@RequestBody AccountDTO accountDTO) {
        var auth = getApiAuthTokenFromContext();
        if (auth.getName() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        var account = accountService.createAccount(auth.getName(), accountDTO);
        if (account.isEmpty()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("User is not present or account does not exist in tinkoff or account already exists in robot");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(account.get());
    }

    @GetMapping()
    public ResponseEntity<?> getAccounts() {
        var auth = getApiAuthTokenFromContext();
        if (auth.getName() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        var accounts = accountService.getAccounts(auth.getName());
        if (accounts == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.status(HttpStatus.OK).body(accounts);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getTinkoffAccounts() {
        var auth = getApiAuthTokenFromContext();
        if (auth.getName() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        var accounts = accountService.getTinkoffAccounts(auth.getName());
        if (accounts == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(accounts);
        }
    }

    @PostMapping("/remove")
    public ResponseEntity<?> removeAccount(@RequestParam("account_id") String accountId) {
        var auth = getApiAuthTokenFromContext();
        if (auth.getName() == null || accountId == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        var account = accountService.removeAccount(auth.getName(), accountId);
        if (account.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(account.get());
        }
    }

    @PostMapping("/update")
    public ResponseEntity<?> updateAccount(@RequestBody AccountDTO accountDTO) {
        var auth = getApiAuthTokenFromContext();
        if (auth.getName() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        var account = accountService.updateAccount(auth.getName(), accountDTO);
        if (account.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(account.get());
        }
    }
}
