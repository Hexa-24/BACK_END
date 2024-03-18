package io.hexa24.yaksok.member.domain.dto;

import io.hexa24.yaksok.member.domain.entity.Color;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.awt.*;

@Getter
@Builder
@ToString
public class MemberReqDTO {
    private Long id;
    private String name;
    private Color color;
}
