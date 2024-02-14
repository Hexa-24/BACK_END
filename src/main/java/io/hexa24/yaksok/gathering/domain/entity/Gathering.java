package io.hexa24.yaksok.gathering.domain.entity;

import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.geo.Point;

import jakarta.persistence.Entity;
import lombok.Builder;
import lombok.Getter;

@Entity
@Getter
@Builder
public class Gathering {
    @Id
    private UUID id;
    private String name;
    private Point point; 
}