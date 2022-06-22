package api.server.service;


import api.server.domain.Board;
import api.server.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;

    public Board getBoard(Long id) {
        return boardRepository.findOne(id);
    }

    public List<Board> getBoardList() {
        return boardRepository.findAll();
    }

    public List<Board> getBoardByName(String name) {
        return boardRepository.findByName(name);
    }

    public void save(Board board) {
        boardRepository.save(board);
    }


}
