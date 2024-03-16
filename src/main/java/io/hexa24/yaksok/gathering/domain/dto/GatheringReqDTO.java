package io.hexa24.yaksok.gathering.domain.dto;

import java.util.UUID;

import io.hexa24.yaksok.gathering.domain.entity.Gathering;
import io.hexa24.yaksok.location.domain.entity.Location;
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
    private Location venus;

    public Gathering toEntity(){
        return Gathering.builder()
                    .id(this.id)
                    .name(this.name)
                    .venue(this.venus)
                    .build();
    }
}
