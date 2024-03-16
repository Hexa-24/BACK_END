package io.hexa24.yaksok.location.domain.entity;

import io.hexa24.yaksok.member.domain.entity.Member;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Getter
@Entity
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Candidate {
    
    @Id
    @Column(name = "candidate_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long memberId;

    private Long gatheringId;

    @ManyToOne
    private Location location;

    @ManyToMany(mappedBy = "voteCandidates")
    private List<Member> voteMembers;

    @ManyToMany(mappedBy = "memoCandidates")
    private List<Member> memoMembers;
}
