package study.group.kjh.account;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AccountController {

    @GetMapping("/sign-up")
    public String signup(Model model) {
        model.addAttribute(new SignUpForm());
        return "signup";
    }
    @GetMapping("/")
    public String home() {
        return "index";
    }
}
