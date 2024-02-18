package io.hexa24.yaksok.location.domain.dto;

import org.springframework.data.geo.Point;

import io.hexa24.yaksok.gathering.domain.entity.Gathering;
import io.hexa24.yaksok.member.domain.entity.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class LocationRespDTO {
    private Long id;
    private Gathering gathering;
    private Member member;
    private String name;
    private Point point;
}
