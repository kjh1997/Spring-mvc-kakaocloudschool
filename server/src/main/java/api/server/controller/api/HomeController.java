package api.server.controller.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {
    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/user")
    public String user() {
        return "user";
    }

    @ResponseBody
    @GetMapping("/admin")
    public String admin() {
        return "admin";
    }


    @GetMapping("/manager")
    public String manager() {
        return "manager";
    }
    @GetMapping("/logout")
    public String logout() {
        return "logout";
    }
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/join")
    public String join() {
        return "join";
    }

    @ResponseBody
    @GetMapping("/joinproc")
    public String joinproc() {
        return "회원가입 완료됨!!";
    }



}
