package com.kjh.miniprj.security;

import com.kjh.miniprj.account.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LoginAuthenticationProvider implements AuthenticationProvider {
    private final AccountRepository accountRepository;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.isAuthenticated().
        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return false;
    }
}
