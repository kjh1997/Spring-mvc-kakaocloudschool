package jpabook.jpashop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class BoardController {

    @GetMapping("/board/new")
    public String createBoard(Model model) {
        System.out.println("Test");
        model.addAttribute("form", new BoardForm());
        return "board/createBoardForm";
    }

    @PostMapping("/board/new")
    public String create(Model model) {

        return "redirect:/boardList";
    }
}
