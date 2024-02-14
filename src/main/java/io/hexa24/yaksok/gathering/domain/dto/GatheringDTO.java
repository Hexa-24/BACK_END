package io.hexa24.yaksok.gathering.domain.dto;

import java.util.UUID;

import org.springframework.data.geo.Point;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GatheringDTO {
    private UUID id;
    private String name;
    private Point point;     
}
