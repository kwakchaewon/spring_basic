package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {

    private final MemberService memberService;

    @Autowired // 스프링 컨테이너에서 멤버 서비스와 연결, MemberService 클래스에서도 @Service 선언: 자동으로 연결하기 위해 / 마찬가지로 @Repository 도 선언 (정형화 된 패턴)
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping(value = "/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }


    // 1. /members/new post 일때,
    // 2. member 객체 생성 후 Name 필드의 값 설정
    // 3. 회원가입 로직을 통해 회원가입
    @PostMapping(value = "/members/new")
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());
        memberService.join(member);
        return "redirect:/";
    }

    // 1. /members get 일때,
    // 2. findMembers()로 회원 조회 후 members 에 담기
    // 3. model에 추가 후 전달
    @GetMapping(value = "/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }

}
