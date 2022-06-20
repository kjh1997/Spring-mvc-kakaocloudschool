package jpabook.jpashop.controller;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class BoardForm {
    private Long id;
    private String title;

    private String content;
    private String author;

    private String tag;


}
