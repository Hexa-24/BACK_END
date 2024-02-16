package io.hexa24.yaksok.location.domain.entity;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Entity
@Builder
@ToString
public class Location {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private UUID gathering_id;
    private String name;
    private String colour;
}