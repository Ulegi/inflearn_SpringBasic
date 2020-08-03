package Hello.HelloSpring.repository;

import Hello.HelloSpring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);
    //회원이 저장소에 저장
    Optional<Member> findById(Long id);
    Optional<Member> findByName(String name);
    //이름이나 Id로 찾아옴
    List<Member> findAll();
    //지금까지 모든 정보를 다 찾아옴
}
