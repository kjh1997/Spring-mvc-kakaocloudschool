package security.example.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import security.example.Repository.BoardRepository;
import security.example.domain.Board;
import security.example.service.boardService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class home {
    private final boardService boardS;


    @GetMapping("/")
    public String home() {
        return "hello world";
    }

    @GetMapping("/user/boardList")
    public String printBoardList() {
        System.out.println("????");
        List<Board> getList = boardS.boardList("2");
        for (Board board : getList) {
            System.out.println(board.getTitle());

        }
        System.out.println("???");
        return "redirect:/";
    }
}
