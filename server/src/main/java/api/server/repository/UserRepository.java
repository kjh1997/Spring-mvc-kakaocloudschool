package api.server.repository;


import api.server.domain.User;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.stream.Collectors;

import static api.server.domain.QUser.user;


@Repository
@Transactional
public class UserRepository {

    private EntityManager em;
    private JPAQueryFactory query;
    @Autowired
    public UserRepository(EntityManager em) {
        this.em = em;
        this.query = new JPAQueryFactory(em); // 이 방식을 스프링 빈 방식으로 할 수 있다.  (QuerydslApplication 에 빈으로 등록)
    }
    public void save(User user) {
        em.persist(user);

    }

    public User getUserId(Long id) {
        return em.find(User.class, id);
    }

    public List<User> getUserList() {
        System.out.println("start");
        return query
                .select(user)
                .from(user)
                .stream().collect(Collectors.toList());
    }


}
