package study.group.kjh.account;

import lombok.Data;
import org.springframework.web.bind.annotation.GetMapping;

@Data
public class SignUpForm {

    private String nickname;
    private String email;
    private String password;

}
