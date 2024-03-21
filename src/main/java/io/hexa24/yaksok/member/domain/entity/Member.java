package io.hexa24.yaksok.member.domain.entity;

import io.hexa24.yaksok.gathering.domain.entity.Gathering;
import io.hexa24.yaksok.location.domain.entity.Candidate;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.awt.*;
import java.util.List;

@Entity
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Member {
    
    @Id
    @Column(name = "member_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name="gathering_id")
    private Gathering gathering;

    private String name;

    @Enumerated(EnumType.STRING)
    private Color color;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="candidate_id")
    private List<Candidate> candidate;

    @ManyToMany
    @JoinTable(name = "vote",
            joinColumns = @JoinColumn(name = "member_id"),
            inverseJoinColumns = @JoinColumn(name = "candidate_id"))
    private List<Candidate> voteCandidates;

    @ManyToMany
    @JoinTable(name = "memo",
            joinColumns = @JoinColumn(name = "member_id"),
            inverseJoinColumns = @JoinColumn(name = "candidate_id"))
    private List<Candidate> memoCandidates;

}
