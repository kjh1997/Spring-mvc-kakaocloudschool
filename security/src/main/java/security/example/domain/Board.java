package security.example.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Board {
    @Id
    @GeneratedValue
    @Column(name= "board_id")
    private Long id;

    private String title;
    private String content;
    private LocalDateTime localDateTime;

    @ManyToOne
    @JoinColumn(name= "member_id")
    private Member member;


}
