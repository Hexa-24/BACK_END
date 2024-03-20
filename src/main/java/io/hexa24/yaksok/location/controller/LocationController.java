package io.hexa24.yaksok.location.controller;

import java.net.URI;
import java.util.List;
import java.util.UUID;

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
import io.hexa24.yaksok.location.domain.dto.CandidateRespDTO;
import io.hexa24.yaksok.location.domain.entity.Location;
import io.hexa24.yaksok.location.service.LocationServiceImpl;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("gatherings/{gatheringId}/members")
@RequiredArgsConstructor
@Slf4j
public class LocationController {
    
    private final LocationServiceImpl locationService;

    /**
     * gatheringId로 Location을 조회하는 GET 메서드 입니다.
     * @author HYS
     * 
     * @param gatheringId Gathering의 PK, Location의 FK
     * @return 생성된 Gathering의 URI를 포함한 ResponseEntity
     */    
    @GetMapping("/locations")
    @ResponseStatus(value = HttpStatus.OK)
    public List<CandidateRespDTO> getLocationList(@PathVariable UUID gatheringId) {
        List<Location> location = locationService.findLocationsByGatheringId(gatheringId);
        return CandidateRespDTO.fromLocations(location);
    }

    @GetMapping("/{memberId}/locations")
    @ResponseStatus(value = HttpStatus.OK)
    public List<CandidateRespDTO> getLocation(@PathVariable UUID gatheringId, @PathVariable Long memberId) {
        List<Location> location = locationService.findLocationsByGatheringIdAndMemberId(gatheringId, memberId);
        return CandidateRespDTO.fromLocations(location);
    }

    @GetMapping("/{memberId}/locations/{locationId}")
    @ResponseStatus(value = HttpStatus.OK)
    public CandidateRespDTO getLocation(@PathVariable Long locationId) {
        Location location = locationService.findLocation(locationId);
        return CandidateRespDTO.fromLocation(location);
    }
         
    @PostMapping("/{memberId}/locations")
    public ResponseEntity<Void> postLocation(@PathVariable UUID gatheringId, @PathVariable Long memberId, @RequestBody LocationReqDTO locationReqDTO, UriComponentsBuilder uriBuilder) {
        Location saved = locationService.addLocation(gatheringId, memberId, locationReqDTO);
        URI urilocation = uriBuilder.path("gatherings/{gatheringId}/members/{memberId}/location/{locationId}")
                                    .buildAndExpand(gatheringId, memberId, saved.getId())
                                    .toUri();
        log.debug("urilocation: " + String.valueOf(urilocation));
        return ResponseEntity.created(urilocation).build();
    }

    @PutMapping("/{memberId}/locations/{locationId}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void putLocation(@PathVariable Long locationId, @RequestBody LocationReqDTO locationReqDTO) {
        Location location = Location.builder()
                                    .id(locationId)
                                    .name(locationReqDTO.getName())
                                    .coordinate(locationReqDTO.getCoordinate().toValue())
                                    .build();        
        locationService.modifyLocation(location);
    }

    @DeleteMapping("/{memberId}/locations/{locationId}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteLocation(@PathVariable Long locationId){
        locationService.removeLocation(locationId);
    }
}
