package api.server.domain.repository;


import api.server.domain.Board;
import static api.server.domain.QBoard.board;
import static api.server.domain.QUser.user;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.lang.reflect.Member;
import java.util.List;



@Repository
@Transactional
public class BoardRepository {
    private final EntityManager em;
    private JPAQueryFactory query;
    public BoardRepository(EntityManager em) {
        this.em = em;
        this.query = new JPAQueryFactory(em); // 이 방식을 스프링 빈 방식으로 할 수 있다.  (QuerydslApplication 에 빈으로 등록)
    }
    public Board findOne(Long id) {
        return em.find(Board.class, id);
    }


    public List<Board> findAll() {
        System.out.println("test");

        return em.createQuery("select b from Board b",Board.class).getResultList();
    }

    public void save(Board board) {
        em.persist(board);
    }

    public List<Board> findByName(String name) {
        return em.createQuery("select b from board b where name like %:name%")
                .setParameter("name", name)
                .getResultList();
    }

    public List<Board> findByNameQuerydsl(String name) {

        return query
                .select( board)
                .from(board)
                .join(board.user, user)
                .where(statusEq(name))
                .fetch();
    }

    private BooleanExpression statusEq(String name) {
        if (name == null) {
            return null;
        }
        return board.user.username.eq(name);
    }


}
