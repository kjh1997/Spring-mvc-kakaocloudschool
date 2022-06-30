package api.server.service;

import api.server.domain.Board;
import api.server.domain.Category;
import api.server.domain.repository.CategoryRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
@Setter
@Getter
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public void save(Category category) {
        categoryRepository.save(category);
    }

    public List<Board> getBoardByCategoryId(String id) {
        return categoryRepository.
                getBoardListByCategoryName(Long.parseLong(id));
    }




}
