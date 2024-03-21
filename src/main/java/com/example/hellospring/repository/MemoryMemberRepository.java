package com.example.hellospring.repository;

import com.example.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

//섹션3 강의 / implements 로 부모인 인터페이스 에서 상속받아서 사용
@Repository
public class MemoryMemberRepository implements MemberRepository
{
    //Map<Key, Value> / Map<변수, 변수> 로 선언한다. Key 는 중복 불가, Value는 중복 가능.
    private static Map<Long, Member> store = new HashMap<>();

    //Long -> int 보다 큰 범위의 데이터 타입. Long을 사용할땐 L을 뒤에 붙인다.
    private static long sequence = 0L;
    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
    }

    //ArrayList<> / 배열같은 느낌. 배열과 달리 길이가 가변적이다. List<Number> l = new ArrayList<>();
    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }
}
