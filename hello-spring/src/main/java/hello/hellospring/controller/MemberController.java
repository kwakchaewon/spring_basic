package hello.hellospring.controller;

import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class MemberController {

    private final MemberService memberService;

    @Autowired // 스프링 컨테이너에서 멤버 서비스와 연결, MemberService 클래스에서도 @Service 선언: 자동으로 연결하기 위해 / 마찬가지로 @Repository 도 선언 (정형화 된 패턴)
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
}
