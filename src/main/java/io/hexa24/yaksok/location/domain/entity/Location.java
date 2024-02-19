package io.hexa24.yaksok.location.domain.entity;

import org.springframework.data.geo.Point;

import io.hexa24.yaksok.gathering.domain.entity.Gathering;
import io.hexa24.yaksok.member.domain.entity.Member;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@Entity
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Location {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="GATHERING_ID")
    private Gathering gathering;

    @ManyToOne
    @JoinColumn(name="MEMBER_ID")
    private Member member;

    private String name;

    private Point point;
}
