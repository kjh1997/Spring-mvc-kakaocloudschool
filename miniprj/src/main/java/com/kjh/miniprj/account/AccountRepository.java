package com.kjh.miniprj.account;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {

    Account findByNickname(String nickname);


    Account findByEmail(String email);

    Account findByNicknameAndPassword(String nickname, String password);
}
