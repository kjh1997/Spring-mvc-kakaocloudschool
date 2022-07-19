package api.server.controller.api;


import api.server.controller.api.dto.boardDTO;
import api.server.domain.Board;
import api.server.domain.Category;
import api.server.domain.User;
import api.server.domain.repository.BoardRepository;
import api.server.domain.repository.UserInterFace;
import api.server.domain.repository.UserRepository;
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
    private final BoardRepository boardRepository;
    private final UserInterFace userInterFace;


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

    @GetMapping("/graph/all")
    public List<Board> getAllByGraph(){
        List<Board> boardList = boardRepository.findAll();
        for (Board b : boardList) {
            System.out.println("==================");
            if(b.getCategory() != null){
                System.out.println(b.getCategory().getName());
            }
            if(b.getUser()!= null){
                System.out.println(b.getUser().getUsername());
            }
            System.out.println(b.getTitle());
            System.out.println(b.getContext());
            System.out.println("==================");
        }
        return boardList;
    }
    @GetMapping("/graph/all2")
    public String getAllByGraph2(){
        List<User> boardList = userInterFace.findAll();
        for (User b : boardList) {
            System.out.println("==================");
//            if(b.getCategory() != null){
//                System.out.println(b.getCategory().getName());
//            }
//            if(b.getUser()!= null){
//                System.out.println(b.getUser().getUsername());
//            }
//            System.out.println(b.getTitle());
//            System.out.println(b.getContext());
            System.out.println("getBoard : " + b.getBoard());
            System.out.println(b.getUsername());
            System.out.println("==================");
        }
        return "success";
    }
}