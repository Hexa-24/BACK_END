package io.hexa24.yaksok.gathering.domain.dto;

import java.util.UUID;

import org.springframework.data.geo.Point;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GatheringRespDTO {
    private UUID id;
    @NotBlank
    private String name;
    private Point point;     
}
