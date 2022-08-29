package com.kjh.miniprj.board;


import com.kjh.miniprj.account.Account;
import com.kjh.miniprj.security.CurrentUser;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.persistence.GeneratedValue;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    @GetMapping("/new")
    public String getBoardForm(@CurrentUser Account account, Model model) {
        model.addAttribute("boardDTO", new BoardDTO());
        return "boardForm";
    }

    @PostMapping("/new")
    public String saveBoardAction(@CurrentUser Account account, BoardDTO boardDTO) {
        boardService.saveBoard(account.getNickname(), boardDTO);
        return "redirect:/";
    }


    @GetMapping("/list")
    public String getBoardList(@CurrentUser Account account, Model model,@PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        model.addAttribute("name", account.getNickname());
        model.addAttribute("posts", boardService.pageList(pageable));
        model.addAttribute("previous", pageable.previousOrFirst().getPageNumber());
        model.addAttribute("next", pageable.next().getPageNumber());
        return "boardList";
    }
}
