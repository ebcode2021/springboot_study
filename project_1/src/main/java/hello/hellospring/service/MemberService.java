package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {

        this.memberRepository = memberRepository;
    }

    /**
     * 회원 가입
     */
    public Long join(Member member){
        long start = System.currentTimeMillis();
        try {
            validateDuplicateMember(member); //중복 회원 검증
            memberRepository.save(member);
            return member.getId();
        } finally { //예외가 터져도 찍어야 하니까
            long finish = System.currentTimeMillis();
            System.out.println("join = " + (finish - start) + "ms");
        }

    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }

    /**
     * 리팩토링 이전 코드
     */
    public Long join2(Member member){
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원");
                });
        memberRepository.save(member);
        return member.getId();
    }

    public Long join3(Member member){
        // 같은 이름이 있는 중복 회원 X
        Optional<Member> result = memberRepository.findByName(member.getName());
        result.ifPresent(m -> {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });
        memberRepository.save(member);
        return member.getId();
    }

}
