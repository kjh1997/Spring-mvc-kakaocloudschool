package security.example.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import security.example.Repository.MemberRepository;
import security.example.domain.Board;
import security.example.domain.Member;

import javax.persistence.EntityManager;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public Member getMember(Long memberId) {
        return memberRepository.findById(memberId);

    }

    public void saveMember(Member member) {
        memberRepository.save(member);
    }


}
