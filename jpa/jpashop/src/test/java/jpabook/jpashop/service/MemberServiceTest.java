package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;


@SpringBootTest
@Transactional
public class MemberServiceTest {
    @Autowired
    MemberRepository memberRepository;

    @Autowired
    MemberService memberService;
    @Autowired
    EntityManager em;

    @Test
    public void 회원가입() throws Exception {
        //given
        Member member = new Member();
        member.setName("kjh");

        //when
        Long saveId = memberService.join(member);
        em.flush();

        //then
        assertEquals(member, memberRepository.findOne(saveId));
        System.out.println(member);
        System.out.println(memberRepository.findOne(saveId));
    }

    @Test
    public void 중복_회원_예외() throws Exception {
        //given
        Member member1 = new Member();
        member1.setName("kjh1");

        Member member2 = new Member();
        member2.setName("kjh1");

        //when
        memberService.join(member1);
        try {
            memberService.join(member2);
        } catch (IllegalStateException e) {
            return;
        }
        System.out.println("?????????????");

        //then
        fail("예외가 발생해야함");
    }

}