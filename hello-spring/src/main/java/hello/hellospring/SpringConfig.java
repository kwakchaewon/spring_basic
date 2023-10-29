package hello.hellospring;

import com.sun.jdi.PrimitiveValue;
import hello.hellospring.repository.JdbcMemberRepository;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import hello.hellospring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {
    
    // 데이터베이스 연결 객체 생성
    private final DataSource dataSource;
    
    // 데이터소스 주입
    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository(){
//        return  new MemoryMemberRepository();
        return new JdbcMemberRepository(dataSource);
    }


}
