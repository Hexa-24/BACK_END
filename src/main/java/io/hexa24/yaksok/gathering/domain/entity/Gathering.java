package io.hexa24.yaksok.gathering.domain.entity;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import io.hexa24.yaksok.gathering.domain.dto.GatheringRespDTO;
import io.hexa24.yaksok.location.domain.entity.Location;
import io.hexa24.yaksok.member.domain.entity.Member;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

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
