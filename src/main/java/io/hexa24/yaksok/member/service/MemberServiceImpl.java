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
    public void addMember() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addMember'");
    }

    @Override
    public void modifyMember() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'modifyMember'");
    }

    @Override
    public void removeMember() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'removeMember'");
    }
    
}
