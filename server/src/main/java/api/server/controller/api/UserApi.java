package api.server.controller.api;

import api.server.domain.Board;
import api.server.domain.User;
import api.server.service.BoardService;
import api.server.service.UserService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserApi {
    private final UserService userService;
    private final BCryptPasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public String createUser(@RequestBody userResponse requestData) {
        if (userService.getUserByName(requestData.getName()) != null) {
            System.out.println("duplicate username");
            return "duplicate";
        }
        User user = new User();
        System.out.println();
        user.setUsername(requestData.getName());
        user.setEmail(requestData.getEmail());
        user.setRoles("ROLE_USER");
        user.setPassword(passwordEncoder.encode(requestData.getPassword()));
        userService.save(user);
        return "success";
    }

    @GetMapping("/userlist")
    public String getUserList() {

        for (User user : userService.getUserList()) {
            System.out.println("name : " + user.getUsername());
        }
        return "success";
    }

    @Data
    @RequiredArgsConstructor
    static class userResponse {
        private Long id;
        private String email;
        private String password;
        private String name;
    }


//    public String loginTest() {
//        return "success";
//    }
}
