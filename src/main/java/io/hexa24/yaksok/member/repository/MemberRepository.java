package io.hexa24.yaksok.member.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import io.hexa24.yaksok.member.domain.entity.Member;

@Repository
public interface MemberRepository extends CrudRepository<Member, Long> {

    
}