package api.server.controller.api;

import api.server.domain.User;
import api.server.service.UserService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RequiredArgsConstructor
@RestController
public class UserApi {
    private final UserService userService;

    @PostMapping("/user/save")
    public String createUser(@RequestBody userResponse requestData) {
        User user = new User();
        user.setName(requestData.getName());
         userService.save(user);
        return "success";
    }

    @GetMapping("/user/userlist")
    public String getUserList() {
        for(User user : userService.getUserList()){
            System.out.println("name : " + user.getName());
        }
        return "success";
    }

    @Data
    @RequiredArgsConstructor
    static class userResponse {
        private Long id;
        private String name;
    }



}
