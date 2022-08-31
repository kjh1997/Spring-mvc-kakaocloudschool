package com.kjh.miniprj.board;

import com.kjh.miniprj.account.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    private final AccountRepository accountRepository;

    public void saveBoard(String nickname,BoardDTO boardDTO) {
        Board board = new Board();
        board.setContext(boardDTO.getContext());
        board.setTitle(boardDTO.getTitle());
        board.setAccount(accountRepository.findByNickname(nickname));
        boardRepository.save(board);

    }

//    public List<Board> getBoardList() {
//        return boardRepository.findAll(Page);
//    }


    public Page<Board> pageList(Pageable pageable) {
        Page<Board> all = boardRepository.findAll(pageable);
        return all;
    }
}
