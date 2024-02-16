package io.hexa24.yaksok.member.service;

import io.hexa24.yaksok.member.domain.entity.Member;

public interface MemberService {
    
    Member findMember(Long id);
    Member addMember(Member member);
    void modifyMember();
    void removeMember();

}
