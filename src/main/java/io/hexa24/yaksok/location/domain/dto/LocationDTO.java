package io.hexa24.yaksok.location.domain.dto;

import io.hexa24.yaksok.location.domain.entity.Location;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class LocationDTO {

    private Long id;
    private String name;
    private AddressDTO address;
    private CoordinateDTO coordinate;

    public static LocationDTO convertToDTO(Location location) {
        return LocationDTO.builder()
                .id(location.getId())
                .name(location.getName())
                .address(location.getAddress().toDTO())
                .coordinate(location.getCoordinate().toDTO())
                .build();
    }

}
