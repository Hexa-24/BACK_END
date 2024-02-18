package io.hexa24.yaksok.member.domain.dto;

import java.util.List;
import java.util.stream.Collectors;

import io.hexa24.yaksok.gathering.domain.entity.Gathering;
import io.hexa24.yaksok.member.domain.entity.Member;
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


    // Member를 MemberRespDTO로 변환하는 메서드
    public static MemberRespDTO fromMember(Member member) {
        MemberRespDTO memberRespDTO = MemberRespDTO.builder()
                                                    .id(member.getId())
                                                    .gathering(member.getGathering())
                                                    .name(member.getName())
                                                    .colour(member.getColour())
                                                    .build();
        return memberRespDTO;
    }

    // List<Member>를 List<MemberRespDTO>로 변환하는 메서드
    public static List<MemberRespDTO> fromMembers(List<Member> members) {
        return members.stream()
                    .map(MemberRespDTO::fromMember)
                    .collect(Collectors.toList());
    }
}
