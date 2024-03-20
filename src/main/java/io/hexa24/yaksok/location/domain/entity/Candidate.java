package io.hexa24.yaksok.location.domain.entity;

import io.hexa24.yaksok.member.domain.entity.Member;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Candidate {
    
    @Id
    @Column(name = "candidate_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long memberId;

    private UUID gatheringId;

    @ManyToOne
    private Location location;

    @ManyToMany(mappedBy = "voteCandidates")
    private List<Member> voteMembers;

    @ManyToMany(mappedBy = "memoCandidates")
    private List<Member> memoMembers;

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "memberId = " + memberId + ", " +
                "gatheringId = " + gatheringId + ", " +
                "location = " + location + ")";
    }
}
