package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

//@Repository
public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        // 요즘에는 null이 반환될 가능성이 있으면, Optional.ofNullable로 감 쌈.
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny(); // 하나라도 찾으면 반환. 없으면 null 반환
    }

    @Override
    public List<Member> findAll() {
        // 자바 실무에서는 List 많이 씀
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }
}
