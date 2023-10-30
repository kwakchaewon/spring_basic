package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.*;


// 인터페이스를 상속 받아 메서드 구현

public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long, Member> store = new HashMap<>();
    private  static long sequence = 0L;

    @Override
    // 1. 멤버 저장 로직
    public Member save(Member member) {
        member.setId(++sequence); // 1) 아이디 setting & 시퀀스 값 +1
        store.put(member.getId(), member); // 2) id값과 멤버 객체 저장
        return member; // 3) 멤버 객체 반환
    }

    @Override
    // 2. id로 검색 로직
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    // 3. Name으로 검색 로직
    public Optional<Member> findByName(String name) {
        return store.values().stream() // 1) 스토어에서 루프를 돌림
                .filter(member -> member.getName().equals(name)) // 2) Lambda 사용: member Name이 파라미터 name과 같은지 확인
                .findAny(); // 3) 찾으면 반환
    }

    @Override
    // 4. 전체 멤버 반환
    public List<Member> findAll() {
        return new ArrayList<>(store.values()); // store 의 values() : 모든 values 반환
    }

    public void clearStore(){
        // 데이터 클리어
        store.clear();
    }
}


