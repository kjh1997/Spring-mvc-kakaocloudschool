package com.kjh.miniprj.account;

import com.kjh.miniprj.security.CurrentUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping(value="/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.isAuthenticated()) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/";
    }

    @GetMapping("/")
    public String getHome(@AuthenticationPrincipal(expression = "#this == 'anonymousUser' ? null : account") Account account, Model model) {
        if (account != null) {
            model.addAttribute("name", account.getNickname());
        }

        return "index";
    }
    @GetMapping("/test")
    public String getHome2() {
        return "index";
    }

    @GetMapping("/login")
    public String loginUserForm(@RequestParam(value = "msg", required = false) String error, Model model) {
        if (error != null) {
            model.addAttribute("LoginFailMessage", error);
        }


        return "loginForm";
    }

    @GetMapping("/register")
    public String SignUpAccount(Model model) {

        model.addAttribute("account", new AccountDTO());
        return "registerForm";
    }
    @PostMapping("/register")
    public String SignUpAccount(Model model, AccountDTO account, Errors errors) {
        if (errors.hasErrors()) {
            return "registerForm";
        }
        System.out.println(account.getEmail());
        int a = accountService.saveAccount(account);
        if (a == 0) {
            return "test";
        }
        return "redirect:/";
    }

}
