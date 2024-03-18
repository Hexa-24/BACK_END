package io.hexa24.yaksok.member.domain.dto;

import java.util.List;
import java.util.stream.Collectors;

import io.hexa24.yaksok.gathering.domain.dto.GatheringRespDTO;
import io.hexa24.yaksok.member.domain.entity.Color;
import io.hexa24.yaksok.member.domain.entity.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class MemberRespDTO {
    private Long id;
    private String name;
    private Color color;


    // Member를 MemberRespDTO로 변환하는 메서드
    public static MemberRespDTO fromMember(Member member) {
        return MemberRespDTO.builder()
                            .id(member.getId())
                            .name(member.getName())
                            .build();
    }

    // List<Member>를 List<MemberRespDTO>로 변환하는 메서드
    public static List<MemberRespDTO> fromMembers(List<Member> members) {
        return members.stream()
                    .map(MemberRespDTO::fromMember)
                    .collect(Collectors.toList());
    }
}
