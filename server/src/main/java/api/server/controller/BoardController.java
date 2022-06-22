package api.server.controller;


import api.server.domain.Board;
import api.server.service.BoardService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;
    @PostMapping("/board/create")
    public String createUser(@RequestBody boardDTO requestBody) {
        Board board = new Board();
        board.setTitle(requestBody.title);
        board.setContext(requestBody.context);
        boardService.save(board);
        return "success";
    }

    @GetMapping("/board/list")
    public String getBoardList() {
        List<Board> boardList = boardService.getBoardList();
        for (Board board : boardList) {
            System.out.println(board.getTitle());
            System.out.println(board.getContext());
        }
        return "success";
    }


    @Data
    static class boardDTO {
        private String title;
        private String context;
        private LocalDateTime localDateTime;

    }
}
