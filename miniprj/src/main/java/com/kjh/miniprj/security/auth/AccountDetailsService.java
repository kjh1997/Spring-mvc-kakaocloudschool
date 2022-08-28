package com.kjh.miniprj.security.auth;

import com.kjh.miniprj.account.Account;
import com.kjh.miniprj.account.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountDetailsService implements UserDetailsService {
    private final AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepository.findByNickname(username);
        return new MakeUserDetails(account);
    }
}
