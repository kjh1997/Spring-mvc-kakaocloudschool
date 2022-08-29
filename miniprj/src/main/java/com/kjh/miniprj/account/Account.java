package com.kjh.miniprj.account;

import com.kjh.miniprj.board.Board;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Setter @Getter
public class Account {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "account_id")
    private Long id;
    @Column(unique = true)
    private String nickname;
    @Column(unique = true)
    private String email;
    private String password;
    private LocalDateTime joined_at;
    private String roles;

    @OneToMany(mappedBy = "account")
    private List<Board> boardList;
    public Account() {
        this.joined_at = LocalDateTime.now();
    }


}
