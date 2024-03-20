package io.hexa24.yaksok.gathering.domain.dto;

import java.time.LocalDate;

import io.hexa24.yaksok.gathering.domain.entity.Gathering;
import io.hexa24.yaksok.location.domain.dto.VenueDTO;
import io.hexa24.yaksok.location.domain.entity.Location;
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
public class GatheringPostReqDTO {

    private String name;
    private LocalDate date;
    private VenueDTO venue;

    public Gathering toEntity(){
        return Gathering.builder()
                    .name(this.name)
                    .date(this.date)
                    .venue(Location.builder()
                            .name(venue.getName())
                            .address(venue.getAddress())
                            .coordinate(venue.getCoordinate())
                            .build()
                    )
                    .build();
    }
}
