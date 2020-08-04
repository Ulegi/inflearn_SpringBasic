package Hello.HelloSpring.repository;

import Hello.HelloSpring.domain.Member;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository {

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;
    //HashMpa : ??
    //sequence는 0,1,2... 키값을 생성해주는 애

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        //회원 아이디를 설정
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
        //요즘은 null이 반환될 가능성이 있으면 Optional 로 감싼다.
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                //람다 함수를 사용하는 것이라고 함
                //파라미터로 넘어온 name이 같은 것인지 확인하는 것
                //같으면 반환
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
        //store.values 는 member들
    }
}
