package io.hexa24.yaksok.location.domain.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class LocationReqDTO {

    private String name;
    private AddressDTO address;
    private CoordinateDTO coordinate;

}
