package io.hexa24.yaksok.member.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import io.hexa24.yaksok.member.domain.entity.Member;

@Repository
public interface MemberRepository extends CrudRepository<Member, Long> {
    
    List<Member> findByGatheringId(UUID gatheringId);
}