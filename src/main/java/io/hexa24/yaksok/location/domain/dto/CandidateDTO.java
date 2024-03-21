package io.hexa24.yaksok.location.domain.dto;

import io.hexa24.yaksok.location.domain.entity.Candidate;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
@ToString
public class CandidateDTO {
    private Long id;
    private LocationDTO location;


    public static List<CandidateDTO> convertToDTO(List<Candidate> candidates) {
        return candidates.stream()
                .map(CandidateDTO::convertToDTO)
                .collect(Collectors.toList());
    }

    public static CandidateDTO convertToDTO(Candidate candidate) {
        return CandidateDTO.builder()
                .id(candidate.getId())
                .location(LocationDTO.convertToDTO(candidate.getLocation()))
                .build();
    }
}
