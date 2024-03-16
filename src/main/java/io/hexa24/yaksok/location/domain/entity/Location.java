package io.hexa24.yaksok.location.domain.entity;

import io.hexa24.yaksok.gathering.domain.entity.Gathering;
import io.hexa24.yaksok.location.domain.value.Address;
import io.hexa24.yaksok.location.domain.value.Coordinate;
import io.hexa24.yaksok.location.domain.entity.Candidate;
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
public class Location {
    
    @Id
    @Column(name = "location_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name="member_id")
    private Member member;

    @OneToMany(mappedBy = "location", fetch = FetchType.LAZY)
    private List<Candidate> candidates;

    private String name;

    @Embedded
    private Address address;

    @Embedded
    private Coordinate coordinate;

}
