package com.example.hellospring.service;

import com.example.hellospring.domain.Member;
import com.example.hellospring.repository.MemberRepository;
import com.example.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//섹션3 강의 / 서비스는 비즈니스 적인 변수를 사용한다. 롤에 맞도록 네이밍하기
@Service
public class MemberService {

    private final MemberRepository memberRepository;

    //Alt + Ins 로 constructor 단축키로 생성
    //Autowired (컴포넌트 스캔 자동의존관계 설정 Autowired, Controller, Service, Repository)
    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    //    회원가입
    public Long join(Member member) {
        //같은 이름이 있는 중복 회원X
        validateDuplicateMember(member); //중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    //Optional의 ifPresent 메소드 사용 >> 특정 결과를 반환하는 대신에 Optional 객체가 감싸고 있는 값이 존재할 경우에만 실행될 로직을 함수형 인자로 반환
    //ifPresent()를 사용하면 람다식으로 활용해 Null체크 없이 사용할 수 객체를 사용할 수 있습니다.
    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
            .ifPresent(m -> {
                throw new IllegalStateException("이미 존재하는 회원입니다.");
            });
    }

//    전체 회원 조회
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
