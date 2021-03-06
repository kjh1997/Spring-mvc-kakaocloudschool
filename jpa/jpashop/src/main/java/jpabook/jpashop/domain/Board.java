package jpabook.jpashop.domain;


import lombok.Getter;
import lombok.Setter;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Board {
    @Id
    @GeneratedValue
    @Column(name = "board_id")
    private Long id;

    private String title;

    private String content;

    private String tag;

    private String author;

    private LocalDateTime boardDate;

    public LocalDateTime getBoardDate() {
        return LocalDateTime.now();
    }


}
