package io.hexa24.yaksok.member.domain.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class MemberPostReqDTO {
    private String name;
}
