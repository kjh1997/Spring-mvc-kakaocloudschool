package com.kjh.miniprj.board;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kjh.miniprj.account.Account;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

import static javax.persistence.FetchType.*;

@Entity
@Getter
@Setter
public class Board {
    @Id
    @Column(name = "board_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String context;
    private LocalDateTime create_at;

    @JsonIgnore
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "account_id")
    private Account account;
    public Board() {
        this.create_at = LocalDateTime.now();
    }


}
