package io.hexa24.yaksok.location.domain.dto;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.geo.Point;

import io.hexa24.yaksok.gathering.domain.entity.Gathering;
import io.hexa24.yaksok.location.domain.entity.Location;
import io.hexa24.yaksok.member.domain.entity.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class LocationRespDTO {
    private Long id;
    private Member member;
    private String name;
    private Point point;

    // Member를 MemberRespDTO로 변환하는 메서드
    public static LocationRespDTO fromLocation(Location location) {
        return LocationRespDTO.builder()
                                                    .id(location.getId())
                                                    .name(location.getName())
                                                    .point(location.getCoordinate())
                                                    .build();
    }

    // List<Member>를 List<MemberRespDTO>로 변환하는 메서드
    public static List<LocationRespDTO> fromLocations(List<Location> members) {
        return members.stream()
                    .map(LocationRespDTO::fromLocation)
                    .collect(Collectors.toList());
    }
}
