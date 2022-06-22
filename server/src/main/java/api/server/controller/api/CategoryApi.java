package api.server.controller.api;

import api.server.domain.Board;
import api.server.domain.Category;
import api.server.service.CategoryService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class CategoryApi {
    private final CategoryService categoryService;

    @PostMapping("/category/create")
    public String createCategory(@RequestBody CategoryDTO requestBody) {
        Category category = new Category();
        category.setName(requestBody.getName());
        categoryService.save(category);

        System.out.println("성공함" + requestBody.getName());
        return "success";
    }

    @GetMapping("/category/board")
    public List<Result>  getBoardListByCategoryName(@RequestBody CategoryDTO2 requestBody) {
        List<Board> boardList = categoryService.getBoardByCategoryId(requestBody.getId())
                .stream().collect(Collectors.toList());
        List<Result> resultList = new ArrayList<>();
        for (Board b : boardList) {
            BoardDTO board = new BoardDTO();
            CategoryDTO categoryDTO = new CategoryDTO();
            board.setContext(b.getContext());
            board.setTitle(b.getTitle());
            board.setId(b.getId());
            categoryDTO.setName(b.getCategory().getName());
            categoryDTO.setId(b.getCategory().getId());

            Result result = new Result(board,categoryDTO);
            resultList.add(result);
        }
        return resultList;
    }

    @Data
    @AllArgsConstructor
    static class Result<T>{
        private T data;
        private CategoryDTO categoryDTO;
    }
    @Data
    static class CategoryDTO {
        private String name;
        private Long id;
    }
    @Data
    static class CategoryDTO2 {
        private String name;
        private String id;
    }
    @Data
    static class BoardDTO {
        private String title;
        private String context;
        private Long id;
    }
}
