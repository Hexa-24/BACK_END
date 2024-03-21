package io.hexa24.yaksok.member.domain.dto;

import io.hexa24.yaksok.location.domain.dto.CandidateDTO;
import io.hexa24.yaksok.member.domain.entity.Color;
import io.hexa24.yaksok.member.domain.entity.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
@ToString
public class MemberDetailRespDTO {
    private Long id;
    private String name;
    private Color color;
    private List<CandidateDTO> candidate;


    // Member를 MemberRespDTO로 변환하는 메서드
    public static MemberDetailRespDTO convertToDTO(Member member) {
        return MemberDetailRespDTO.builder()
                            .id(member.getId())
                            .name(member.getName())
                            .candidate(CandidateDTO.convertToDTO(member.getCandidate()))
                            .build();
    }

    // List<Member>를 List<MemberRespDTO>로 변환하는 메서드
    public static List<MemberDetailRespDTO> convertToDTO(List<Member> members) {
        return members.stream()
                    .map(MemberDetailRespDTO::convertToDTO)
                    .collect(Collectors.toList());
    }
}
