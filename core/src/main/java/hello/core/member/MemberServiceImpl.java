package hello.core.member;

import hello.core.discount.FixDiscountPolicy;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;

public class MemberServiceImpl implements MemberService{
    private MemberRepository memberRepository; // 데이터베이스 불러옴

    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member){
        memberRepository.save(member);
    }
    @Override
    public Member findMember(Long memberId){
        return memberRepository.findById(memberId);
    }


    // test
    public MemberRepository getMemberRepository(){
        return memberRepository;
    }
}
