package io.hexa24.yaksok.member.domain.dto;

import io.hexa24.yaksok.gathering.domain.entity.Gathering;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class MemberRespDTO {
    private Long id;
    private Gathering gathering;
    private String name;
    private String colour;
}
