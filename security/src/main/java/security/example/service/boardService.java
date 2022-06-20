package security.example.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import security.example.Repository.BoardRepository;
import security.example.domain.Board;

import java.util.List;


@Service
@RequiredArgsConstructor
public class boardService {
    private final BoardRepository boardRepository;

    public List<Board> boardList(String member_id) {
        return boardRepository.boardList(member_id);
    }

}
