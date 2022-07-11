package api.server.controller.api;


import api.server.controller.api.dto.boardDTO;
import api.server.domain.Board;
import api.server.domain.Category;
import api.server.service.BoardService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardApi {
    private final BoardService boardService;
    @PostMapping("/create")
    public String createUser(@RequestBody boardDTO requestBody) {
        boardService.save(requestBody);
        return "success";
    }

    @GetMapping("/list")
    public String getBoardList() {
        List<Board> boardList = boardService.getBoardList();
        for (Board board : boardList) {
            System.out.println(board.getTitle());
            System.out.println(board.getContext());
        }
        return "success";
    }
}