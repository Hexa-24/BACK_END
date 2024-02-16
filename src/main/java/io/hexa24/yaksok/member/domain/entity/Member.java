package io.hexa24.yaksok.member.domain.entity;

import java.util.UUID;

import io.hexa24.yaksok.gathering.domain.entity.Gathering;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Entity
@Getter
@Builder
@ToString
public class Member {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name="GATHERING_ID")
    private Gathering gathering;

    private String name;
    
    private String colour;

}
