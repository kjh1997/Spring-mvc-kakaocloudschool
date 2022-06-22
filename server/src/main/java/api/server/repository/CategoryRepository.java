package api.server.repository;

import api.server.domain.Board;
import api.server.domain.Category;
import api.server.domain.User;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

import static api.server.domain.QUser.user;

@Repository
@Transactional
public class CategoryRepository {
    private EntityManager em;
    private JPAQueryFactory queryFactory;
    public CategoryRepository(EntityManager em) {
        this.em = em;
        this.queryFactory = new JPAQueryFactory(em); // 이 방식을 스프링 빈 방식으로 할 수 있다.  (QuerydslApplication 에 빈으로 등록)
    }

    public void save(Category category) {
        em.persist(category);
    }

//    public List<Board> getBoardByCategoryName(String name) {
//        return queryFactory.select(board).from(board)
//    }
}
