package io.hexa24.yaksok.member.service;

import java.util.List;
import java.util.UUID;

import io.hexa24.yaksok.member.domain.entity.Member;

public interface MemberService {
    
    List<Member> findAllMembers(UUID gatheringId);
    Member findMember(Long id);
    Member addMember(Member member);
    void modifyMember(Member member);
    void removeMember(Long id);
    Member findMemberByGatheringIdAndMemberId(UUID gatheringId, Long memberId);
}
