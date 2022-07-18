package com.kjh5.settings;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kjh5.account.AccountService;
import com.kjh5.account.CurrentUser;
import com.kjh5.domain.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class SettingsController {
    private final AccountService accountService;
    private static final String SETTINGS_PROFILE_VIEW_NAME = "settings/profile";

    @GetMapping("/settings/profile")
    public String profileUpdateForm(@CurrentUser Account account, Model model) {
        model.addAttribute(account);
        model.addAttribute(new Profile(account));
        return SETTINGS_PROFILE_VIEW_NAME;
    }

    @PostMapping("/settings/profile")
    public String updateProfile(@CurrentUser Account account, @Valid Profile profile, Errors errors, Model model) {
        System.out.println("length : " + account.getProfileImage());
        if (errors.hasErrors()) {
            model.addAttribute(account);
            return SETTINGS_PROFILE_VIEW_NAME;
        }
        accountService.updateProfile(account, profile);
        return "redirect:/" + SETTINGS_PROFILE_VIEW_NAME;
    }
}
