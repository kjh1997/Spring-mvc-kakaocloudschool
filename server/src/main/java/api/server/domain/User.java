package api.server.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
@Getter
@Setter
public class  User {
    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private Long id;
    @Column(unique = true)
    private String username;

    private String email;
    private String password;
    private String roles;

    @OneToMany(mappedBy = "user")
    private List<Board> board = new ArrayList<>();
    public List<String> getRoleList(){
        if(this.roles.length() > 0){
            return Arrays.asList(this.roles.split(","));
        }
        return new ArrayList<>();
    }
}
