package io.hexa24.yaksok.gathering.domain.dto;

import java.util.UUID;

import org.springframework.data.geo.Point;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class GatheringRespDTO {
    private UUID id;
    @NotBlank
    private String name;
    private Point point;     
}
