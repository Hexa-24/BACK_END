package io.hexa24.yaksok.gathering.domain.entity;

import java.util.UUID;

import org.springframework.data.geo.Point;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Entity
@Getter
@Builder
@ToString
public class Gathering {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    private Point point; 
}
