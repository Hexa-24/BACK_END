package io.hexa24.yaksok.gathering.domain.dto;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


import io.hexa24.yaksok.gathering.domain.entity.Gathering;
import io.hexa24.yaksok.location.domain.dto.AddressDTO;
import io.hexa24.yaksok.location.domain.dto.CandidateRespDTO;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class GatheringRespDTO { 

    private UUID id;

    @NotBlank
    private String name;

    private CandidateRespDTO venue;

    // Member를 MemberRespDTO로 변환하는 메서드
    public static GatheringRespDTO fromGathering(Gathering gathering) {
        return GatheringRespDTO.builder()
                                .id(gathering.getId())
                                .name(gathering.getName())
                                .venue(CandidateRespDTO.fromLocation(gathering.getVenue()))
                                .build();
    }

    // List<Member>를 List<MemberRespDTO>로 변환하는 메서드
    public static List<GatheringRespDTO> fromGatherings(List<Gathering> members) {
        return members.stream()
                    .map(GatheringRespDTO::fromGathering)
                    .collect(Collectors.toList());
    }

    public static GatheringRespDTO toRespDTO(Gathering gathering){
        return GatheringRespDTO.builder()
                .id(gathering.getId())
                .name(gathering.getName())
                .venue(CandidateRespDTO.fromLocation(gathering.getVenue()))
                .build();
    }
}
