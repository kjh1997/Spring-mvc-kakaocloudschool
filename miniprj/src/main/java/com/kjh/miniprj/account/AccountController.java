package com.kjh.miniprj.account;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;

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
