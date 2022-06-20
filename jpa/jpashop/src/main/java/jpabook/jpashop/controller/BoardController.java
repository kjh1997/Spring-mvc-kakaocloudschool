package jpabook.jpashop.controller;

import jpabook.jpashop.domain.Board;
import jpabook.jpashop.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;
    @GetMapping("/board/new")
    public String createBoard(Model model) {
        model.addAttribute("form", new BoardForm());
        return "board/createBoardForm";
    }

    @PostMapping("/board/new")
    public String create(BoardForm form) {
        Board board = new Board();
        board.setBoardDate(board.getBoardDate());
        board.setAuthor(form.getAuthor());
        board.setTitle(form.getTitle());
        board.setTag(form.getTag());
        board.setContent(form.getContent());
        boardService.saveBoard(board);
        return "redirect:/";
    }

    @GetMapping("/boards")
    public String list(Model model) {
        List<Board> boards = boardService.findItems();
        model.addAttribute("items", boards);
        return "board/boardList";
    }
}
