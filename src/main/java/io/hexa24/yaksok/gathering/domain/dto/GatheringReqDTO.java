package io.hexa24.yaksok.gathering.domain.dto;

import java.util.UUID;

import org.springframework.data.geo.Point;

import io.hexa24.yaksok.gathering.domain.entity.Gathering;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
public class GatheringReqDTO {
    private UUID id;
    @NotBlank
    private String name;
    @NotNull
    private Point point;     


    public Gathering toEntity(){
        return Gathering.builder()
                    .id(this.id)
                    .name(this.name)
                    .point(this.point)
                    .build();
    }
}
