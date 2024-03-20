package io.hexa24.yaksok.gathering.domain.entity;

import io.hexa24.yaksok.location.domain.entity.Location;
import io.hexa24.yaksok.member.domain.entity.Member;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Gathering {
    
    @Id
    @Column(name = "gathering_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private LocalDate date;

    private String name;

    @OneToOne(cascade = CascadeType.ALL)   // location 테이블과 단방향 맵핑
    @JoinColumn(name = "location_id")
    private Location venue;

    @Builder.Default
    @OneToMany(mappedBy = "gathering", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private List<Member> members = new ArrayList<>();

    public void setMembers(List<Member> members) {
        this.members = members;
    }


}
