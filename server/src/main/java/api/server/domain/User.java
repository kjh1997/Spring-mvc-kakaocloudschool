package api.server.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class  User {
    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private Long id;
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Board> board = new ArrayList<>();

}
