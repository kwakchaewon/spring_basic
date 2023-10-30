package hello.hellospring.domain;

// 도메인: 비즈니스 객체 (데이터베이스에 저장 관리)
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

// Entity 어노테이션을 통해 jpa 관리하의 Entity임을 선언
@Entity
public class Member {

    // Id: PK 선언
    // eneratedValue(strategy = GenerationType.IDENTITY): 자동으로 ID 값 생성함을 알려주고 매핑
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    // Column 명과 일치하지 않을 경우 @Column(name=) 으로 매핑

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}