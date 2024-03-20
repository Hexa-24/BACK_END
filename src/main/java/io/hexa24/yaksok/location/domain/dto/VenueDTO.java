package io.hexa24.yaksok.location.domain.dto;

import io.hexa24.yaksok.location.domain.value.Address;
import io.hexa24.yaksok.location.domain.value.Coordinate;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class VenueDTO {

    private String name;
    private AddressDTO address;
    private CoordinateDTO coordinate;
    
}
