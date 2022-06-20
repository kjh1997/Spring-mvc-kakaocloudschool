package security.example.domain;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
public class Member {
    @Id
    @Column(name = "member_id")
    private Long id;

    @Column(length = 100, nullable = false, unique = true)
    private String name;

    @Column(length = 100, nullable = false)
    private String passwd;

    @OneToMany(mappedBy = "member")
    private List<Board> boardList = new ArrayList<>();

}

