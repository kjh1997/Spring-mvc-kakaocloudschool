package api.server.repository;

import api.server.domain.Board;
import api.server.domain.Category;
import api.server.domain.QBoard;
import api.server.domain.User;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.stream.Collectors;

import static api.server.domain.QCategory.category;
import static api.server.domain.QUser.user;
import static api.server.domain.QBoard.board;

@Repository
@Transactional
public class CategoryRepository {
    private EntityManager em;
    private JPAQueryFactory query;
    public CategoryRepository(EntityManager em) {
        this.em = em;
        this.query = new JPAQueryFactory(em); // 이 방식을 스프링 빈 방식으로 할 수 있다.  (QuerydslApplication 에 빈으로 등록)
    }

    public Category findOne(Long id) {
        return em.find(Category.class, id);
    }

    public void save(Category category) {
        em.persist(category);
    }

//    public List<Board> getBoardByCategoryName(String name){
//        return query
//                .select(board)
//                .from(category)
//                .where(category.name.eq(name))
//                .join(board).fetch()
//                .stream().collect(Collectors.toList());
//    }

    public List<Board> getBoardListByCategoryName(Long category_id) {
        return query
                .select(board)
                .from(board)
                .join(board.category, category).fetch()
                .stream().collect(Collectors.toList());
    }
}
