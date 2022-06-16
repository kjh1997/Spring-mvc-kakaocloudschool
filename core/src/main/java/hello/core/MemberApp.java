package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;

public class MemberApp {
    public static void main(String[] args) {
        AppConfig appConfig = new AppConfig();
        MemberService memberService = appConfig.memberService(); // 서비스 생성 << 데이터베이스에 있음.

        Member member = new Member(1l, "memberA", Grade.VIP); // DTO 객체
        memberService.join(member); // 서비스의 join 메소드 사용
        Member findMember = memberService.findMember(1l); // 서비스의 findMember 사용
        System.out.println("new member : "+ member.getName());
        System.out.println("find Member : " + findMember.getName());
    }
}
