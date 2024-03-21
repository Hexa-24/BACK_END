package io.hexa24.yaksok.location.domain.entity;

import io.hexa24.yaksok.location.domain.entity.Candidate;
import io.hexa24.yaksok.member.domain.entity.Member;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class Vote {
    @Id
    @Column(name="vote_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id") // 외래 키 명시
    private Member member;

    @ManyToOne
    @JoinColumn(name = "candidate_id") // 외래 키 명시
    private Candidate candidate;
}
