package com.kjh.miniprj.account;


import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public int saveAccount(AccountDTO accountDTO) {
        int re = findAccount(accountDTO.getEmail());
        if (re == 0) {
            re = findAccount(accountDTO.getUsername());
        }
        if (re == 0){
            Account account = new Account();
            account.setEmail(accountDTO.getEmail());
            account.setNickname(accountDTO.getUsername());
            account.setPassword(bCryptPasswordEncoder.encode(accountDTO.getPassword()));
            account.setRoles("ROLE_USER");
            accountRepository.save(account);
            return 1;
        }
        return 0;
    }
    @Transactional(readOnly = true)
    public int findAccount(String nicknameOrEmail) {
        Account acc = accountRepository.findByNickname(nicknameOrEmail);
        if (acc != null) {
            return 1;
        }
        Account acc2 = accountRepository.findByEmail(nicknameOrEmail);
        if (acc2 != null) {
            return 1;
        }
        return 0;
    }








}
