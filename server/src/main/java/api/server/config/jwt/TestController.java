package api.server.config.jwt;

import api.server.controller.api.UserApi;
import api.server.service.UserService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TestController {
    private final UserService userService;
        @GetMapping("/login")
    public String login(@RequestBody userResponse requestData) {
        System.out.println(requestData.getPassword());
        return userService.login(requestData.getName(), requestData.getPassword());
    }


    @GetMapping("/api/v1/user/test")
    public String apiController() {
        return "/api/v1/user/test : success";
    }

    @Data
    @RequiredArgsConstructor
    static class userResponse {
        private Long id;
        private String email;
        private String password;
        private String name;
    }
}
