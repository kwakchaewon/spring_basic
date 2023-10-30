package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository{


    // EntityManager: DB와의 통신 담당
    private final EntityManager em;

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        // persist(): jpa 가 insert 쿼리 만들어서 집어넣고 set id 까지 해줌
        em.persist(member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String name) {

        // findByName 의 경우 jpql 이라는 객체지향 쿼리를 사용
        // pk 기반이 아닌 것들은 jpql 작성
        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();

        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {

        // 객체 대상 쿼리 날리기, 객체 자체를 select
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }
}
