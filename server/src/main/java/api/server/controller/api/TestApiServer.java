package api.server.controller.api;

import api.server.controller.api.dto.boardDTO;
import api.server.domain.Board;
import api.server.repository.BoardInterface;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class TestApiServer {
    private final BoardInterface boardRepository2;
    @GetMapping("/test/api1")
    public List<Board> testApi() {
        return boardRepository2.findAll().stream().collect(Collectors.toList());
    }

    @GetMapping("/test/api2")
    public Optional<List<Board>> test3Api(@RequestBody boardDTO context) {
        return boardRepository2.findByContext(context.getContext());
    }
    @GetMapping("/test/api3")
    public Optional<List<Board>> test4Api(@RequestBody boardDTO context) {
        return boardRepository2.findByTitle(context.getTitle());
    }






    @Data
    @AllArgsConstructor
    static class Result<T> {
        private T data;
        private CategoryApi.CategoryDTO categoryDTO;
    }
}
