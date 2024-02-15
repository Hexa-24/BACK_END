package io.hexa24.yaksok.gathering.domain.dto;

import java.util.UUID;

import org.springframework.data.geo.Point;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GatheringReqDTO {
    @NotBlank
    private String name;
    @NotNull
    private Point point;     
}
