package io.hexa24.yaksok.member.service;

import org.springframework.stereotype.Service;

import io.hexa24.yaksok.member.domain.entity.Member;
import io.hexa24.yaksok.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;

    @Override
    public Member findMember(Long id) {
        return memberRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    @Override
    public Member addMember(Member member) {
        return memberRepository.save(member);
    }

    @Override
    public void modifyMember(Member member) {
        if(memberRepository.findById(member.getId()) != null){
            memberRepository.save(member);
        }else{
            throw new RuntimeException("해당되는 member 정보가 없어 수정 불가합니다.")
        }
    }

    @Override
    public void removeMember() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'removeMember'");
    }
    
}
