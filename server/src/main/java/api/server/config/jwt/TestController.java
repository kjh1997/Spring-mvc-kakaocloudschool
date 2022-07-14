package api.server.config.jwt;

import api.server.Redis.LoginResponseDto;
import api.server.service.JwtService;
import api.server.service.UserService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TestController {
    private final UserService userService;
    private final JwtService jwtService;
    @PostMapping("/login")
    public String login(@RequestBody userResponse requestData) {
        System.out.println(requestData.getPassword());
        return userService.login(requestData.getName(), requestData.getPassword());
    }


    @GetMapping("/refresh")
    public String tokenGeneration(@RequestBody LoginResponseDto loginResponseDto) {
        return jwtService.tokenRefresh(loginResponseDto);
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
