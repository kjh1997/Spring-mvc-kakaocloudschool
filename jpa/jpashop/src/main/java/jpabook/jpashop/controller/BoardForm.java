package jpabook.jpashop.controller;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class BoardForm {
    private Long id;
    private String title;

    private int content;
    private String author;

    private String tag;
    private LocalDateTime localDateTime;

}
