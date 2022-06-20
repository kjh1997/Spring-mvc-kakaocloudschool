package security.example.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import security.example.Repository.BoardRepository;
import security.example.controller.object.MemberForm;
import security.example.domain.Board;
import security.example.domain.Member;
import security.example.service.MemberService;

import javax.validation.Valid;
import java.util.List;


@Controller
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;
    private final BoardRepository boardRepository;

    @GetMapping("/member")
    public String create(Model model) {
        model.addAttribute("item", new MemberForm());
        return "member/memberForm";
    }

    @PostMapping("/member/save")
    public String saveMember(@Valid MemberForm form, BindingResult result) {
        if (result.hasErrors()) {
            return "member/memberForm";
        }
        Member member = new Member();
        member.setName(form.getName());
        member.setPasswd(form.getPasswd());
        memberService.saveMember(member);
        return "redirect:/";
    }


    @GetMapping("/test")
    public String test() {
        return "hello test";
    }
}
