package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService;

    // clear 해주기 위한 선언
    MemoryMemberRepository memberRepository;

    // 테스트 실행할 때마다 객체 생성
    @BeforeEach
    public void beforeEach(){
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @Test
    void join() {
        // given
        Member member= new Member();
        member.setName("hello");

        //when
        Long saveId = memberService.join(member);

        // then
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());

    }

    // 테스트는 증명 코드만큼이나 예외를 처리하는 로직도 중요함
    // 중복회원예외 테스트
    @Test
    public void duplicatedMember(){
        // given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");
        
        // when
        memberService.join(member1);

        // 아래 로직 실행 중 IllegalStateException이 발생한다면
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));

        // 메시지 검증
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

/*
 2번째 join 으로 멤버 넣으면 spring 이름이 똑같아서 예외가 터져버림
        try {
            memberService.join(member2);
            fail();
        }catch (IllegalStateException e){
            assertThat(e.getMessage())
                    .isEqualTo("이미 존재하는 회원입니다.");       }
 then
*/


    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}