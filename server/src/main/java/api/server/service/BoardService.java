package api.server.service;


import api.server.controller.api.dto.boardDTO;
import api.server.domain.Board;
import api.server.domain.Category;
import api.server.domain.User;
import api.server.repository.BoardInterface;
import api.server.repository.BoardRepository;
import api.server.repository.CategoryRepository;
import api.server.repository.UserRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    public Board getBoard(Long id) {
        return boardRepository.findOne(id);
    }

    public List<Board> getBoardList() {
        return boardRepository.findAll();
    }

    public List<Board> getBoardByName(String name) {
        return boardRepository.findByName(name);
    }

    public void save(@RequestBody boardDTO boardData) {
        User user = userRepository.findOne(Long.parseLong(boardData.getUser_id()));
        Category category = categoryRepository.findOne(Long.parseLong(boardData.getCategory_id()));
        Board board = new Board();
        board.setCategory(category);
        board.setUser(user);
        board.setContext(boardData.getContext());
        board.setTitle(boardData.getTitle());

        boardRepository.save(board);
    }

//    public List<Board> findByName(String name) {
//        return boardInterface.findByName(name);
//    }


}
