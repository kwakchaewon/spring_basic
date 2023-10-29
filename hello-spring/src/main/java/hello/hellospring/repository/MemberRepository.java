package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

// 데이터 베이스에 접근, 도메인 객체를 DB에 저장하고 관리하는 추상 클래스가 담긴 인터페이스 선언

public interface MemberRepository {

    Member save(Member member); // 멤버 저장
    Optional<Member> findById(Long id); // id로 회원 찾기
    Optional<Member> findByName(String Name); // 이름으로 회원 찾기
    List<Member> findAll(); // 회원리스트 반환

    
    
}
