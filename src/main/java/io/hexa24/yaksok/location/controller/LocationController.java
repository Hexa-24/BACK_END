package io.hexa24.yaksok.location.controller;

import java.net.URI;
import java.util.List;
import java.util.UUID;

import io.hexa24.yaksok.location.domain.entity.Candidate;
import io.hexa24.yaksok.location.service.LocationService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import io.hexa24.yaksok.location.domain.dto.LocationReqDTO;
import io.hexa24.yaksok.location.domain.dto.CandidateDTO;
import io.hexa24.yaksok.location.domain.entity.Location;
import io.hexa24.yaksok.location.service.LocationServiceImpl;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("gatherings/{gatheringId}/members")
@RequiredArgsConstructor
@Slf4j
public class LocationController {
    
    private final LocationService locationService;

    /**
     * gatheringId로 Location을 조회하는 GET 메서드 입니다.
     * @author HYS
     *
     * @param gatheringId Gathering의 PK
     * @return 생성된 Gathering의 URI를 포함한 ResponseEntity
     */
    @GetMapping("/candidates")
    @Operation(summary = "Get candidates by gatheringId")
    public ResponseEntity<List<CandidateDTO>> getCandidateList(@PathVariable UUID gatheringId) {
        List<Candidate> candidates = locationService.findLocationsByGatheringId(gatheringId);
        List<CandidateDTO> candidateDTOS = CandidateDTO.convertToDTO(candidates);
        return ResponseEntity.ok(candidateDTOS);
    }

    @PostMapping("/{memberId}/candidates")
    @Operation(summary = "Create candidate with checking the existing location data")
    public ResponseEntity<Void> postLocation(@PathVariable UUID gatheringId, @PathVariable Long memberId, @RequestBody LocationReqDTO locationReqDTO, UriComponentsBuilder uriBuilder) {
        Location saved = locationService.addLocation(gatheringId, memberId, locationReqDTO);
        URI urilocation = uriBuilder.path("gatherings/{gatheringId}/members/{memberId}/location/{locationId}")
                .buildAndExpand(gatheringId, memberId, saved.getId())
                .toUri();
        return ResponseEntity.created(urilocation).build();
    }

    @GetMapping("/{memberId}/candidates/{candidateId}")
    @ResponseStatus(value = HttpStatus.OK)
    public CandidateDTO getLocation(@PathVariable Long candidateId) {
        Candidate candidate = locationService.findLocation(candidateId);
        return CandidateDTO.convertToDTO(candidate);
    }

    @DeleteMapping("/{memberId}/candidates/{candidateId}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteLocation(@PathVariable Long candidateId){
        locationService.removeLocation(candidateId);
    }
}
