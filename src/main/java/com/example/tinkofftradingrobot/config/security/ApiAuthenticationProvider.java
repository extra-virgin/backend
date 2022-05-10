package com.example.tinkofftradingrobot.config.security;

import com.example.tinkofftradingrobot.model.UserEntity;
import com.example.tinkofftradingrobot.repository.UserRepo;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class ApiAuthenticationProvider implements AuthenticationProvider {

    private final UserRepo userRepo;

    public ApiAuthenticationProvider(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        ApiAuthenticationToken apiAuthentication = (ApiAuthenticationToken) authentication;
        var userOpt = userRepo.findByToken(apiAuthentication.getName());
        if (userOpt.isPresent()) {
            UserEntity user = userOpt.get();
            return new ApiAuthenticationToken(user.getToken(), user.getId(), true);
        } else {
            throw new UnknownUserException("Could not find user with Token: " + apiAuthentication.getName());
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return ApiAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
