package io.hexa24.yaksok.memo.domain.entity;

import io.hexa24.yaksok.location.domain.entity.Candidate;
import io.hexa24.yaksok.member.domain.entity.Member;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
public class Memo {
    @Id
    @Column(name="memo_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String memoText;

    @ManyToOne
    @JoinColumn(name = "member_id") // 외래 키 명시
    private Member member;

    @ManyToOne
    @JoinColumn(name = "candidate_id") // 외래 키 명시
    private Candidate candidate;
}
