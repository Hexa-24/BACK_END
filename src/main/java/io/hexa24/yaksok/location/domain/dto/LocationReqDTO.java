package io.hexa24.yaksok.location.domain.dto;

import io.hexa24.yaksok.location.domain.entity.Location;
import org.springframework.data.geo.Point;

import io.hexa24.yaksok.gathering.domain.entity.Gathering;
import io.hexa24.yaksok.location.domain.value.Coordinate;
import io.hexa24.yaksok.member.domain.entity.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class LocationReqDTO {
    private Long id;
    private Gathering gathering;
    private Member member;
    private String name;
    private Coordinate coordinate;

}
