package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class MemoryMemberRepositoryTest {

    // 1. 테스트 할 객체 선언
    MemoryMemberRepository repository = new MemoryMemberRepository();

    // 5. 현재 클래스 테스트 메소드 실행 이후 실행되는 메소드. 데이터 클리어
    // 테스트는 순서와 상관없이 서로 의존관계 없이 설계 되어야한다!
    @AfterEach //
    public void afterEach(){
        repository.clearStore();
    }

    // 2. Test 어노테이션 선언 후 실행하면 메소드가 실행
    @Test
    public void save(){
        // 3. 메인 메소드 쓰듯이 작성하면서 테스트
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        Member result = repository.findById(member.getId()).get(); // get(): optional 에서 값 꺼낼때
        
        // 4. result, member 가 같은 객체인지 비교
        //System.out.println("result = " + (member==result));
        Assertions.assertEquals(result, member);
        
        // 값이 다르면 오류 반환
        //Assertions.assertEquals(result, null    );

        org.assertj.core.api.Assertions.assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        // 테스트 성공
        Member result = repository.findByName("spring1").get();

        // 테스트 실패
        // Member result = repository.findByName("spring2").get();

        org.assertj.core.api.Assertions.assertThat(result).isEqualTo(member1);

    }

    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result =  repository.findAll();

        org.assertj.core.api.Assertions.assertThat(result.size()).isEqualTo(2);
    }


}
