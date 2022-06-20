package security.example.Repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import security.example.domain.Board;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class BoardRepository {
    private final EntityManager em;
    public List<Board> boardList(String member_id) {
        return em.createQuery("select b from Board b where member_id = :member_id",Board.class)
                .setParameter("member_id", 2)
                .getResultList();
    }
}
