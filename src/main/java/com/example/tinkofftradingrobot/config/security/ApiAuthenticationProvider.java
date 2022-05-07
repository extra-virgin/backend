package com.example.tinkofftradingrobot.config.security;

import com.example.tinkofftradingrobot.model.AccountEntity;
import com.example.tinkofftradingrobot.repository.AccountRepo;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class ApiAuthenticationProvider implements AuthenticationProvider {

    private final AccountRepo accountRepo;

    public ApiAuthenticationProvider(AccountRepo accountRepo) {
        this.accountRepo = accountRepo;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        ApiAuthenticationToken demoAuthentication = (ApiAuthenticationToken) authentication;
        boolean isPresent = accountRepo.existsAccountEntitiesByTokenEquals(demoAuthentication.getName());

        if(!isPresent){
            throw new UnknownUserException("Could not find user with Token: " + demoAuthentication.getName());
        }
        // don't return null
        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return ApiAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
