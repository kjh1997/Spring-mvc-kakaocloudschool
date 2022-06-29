package kjh.jwt.controller;

import kjh.jwt.model.User;
import kjh.jwt.repository.UserRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
@RequiredArgsConstructor
@RestController
public class RestApiController {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    @GetMapping("/home")
    public String create() {
        return "test";
    }

    @PostMapping("/token")
    public String token() {
        return "success";
    }


    @PostMapping("/test")
    public String join(@RequestBody User user2) {
        User user = new User();
        user.setUsername("kjh");
        user.setPassword("123");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles("ROLE_USER");
        userRepository.save(user);
        return "회원가입 완료";
    }




    @Getter
    @Setter
    static class UserDTO{
        private String username;
        private String password;
    }
}
