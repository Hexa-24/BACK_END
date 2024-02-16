package io.hexa24.yaksok.member.domain.dto;

import java.util.UUID;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class MemberRespDTO {
    private Long id;
    private UUID gathering_id;
    private String name;
    private String colour;
}
