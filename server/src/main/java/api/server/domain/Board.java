package api.server.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import java.time.LocalDateTime;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@Setter
public class Board   {
    @Id
    @GeneratedValue
    @Column(name = "board_id")
    private Long id;

    private String title;
    private String context;
    private LocalDateTime localDateTime;

    @JsonIgnore
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name="category_id")
    private Category category;

    @JsonIgnore
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public Board(){
        this.localDateTime = LocalDateTime.now();
    }
}
