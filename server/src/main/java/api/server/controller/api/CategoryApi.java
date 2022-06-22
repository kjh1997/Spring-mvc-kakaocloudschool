package api.server.controller.api;

import api.server.domain.Board;
import api.server.domain.Category;
import api.server.service.CategoryService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
    public String getBoardListByCategoryName(@RequestBody CategoryDTO requestBody) {
        List<Board> boardList = categoryService.getBoardByCategoryId(requestBody.getId());
        for (Board b : boardList) {
            System.out.println(b.getTitle());
        }
        return "success";m
    }

    @Data
    static class CategoryDTO {
        private String name;
        private String id;
    }

}
