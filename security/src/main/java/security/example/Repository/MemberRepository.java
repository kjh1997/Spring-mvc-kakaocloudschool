package security.example.Repository;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import security.example.domain.Member;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class MemberRepository {
    private final EntityManager em;

    public Member findById(Long memberId) {
        return em.find(Member.class, memberId);
    }

    public void save(Member member) {
        em.persist(member);
    }

}
