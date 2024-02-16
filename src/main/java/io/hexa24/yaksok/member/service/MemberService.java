package io.hexa24.yaksok.member.service;

import io.hexa24.yaksok.member.domain.entity.Member;

public interface MemberService {
    
    Member findMember(Long id);
    void addMember();
    void modifyMember();
    void removeMember();

}
