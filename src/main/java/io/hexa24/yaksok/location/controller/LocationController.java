package io.hexa24.yaksok.location.controller;

import java.net.URI;
import java.util.List;
import java.util.UUID;

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
import io.hexa24.yaksok.location.domain.dto.LocationRespDTO;
import io.hexa24.yaksok.location.domain.entity.Location;
import io.hexa24.yaksok.location.service.LocationServiceImpl;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("gatherings/{gatheringId}/members")
@RequiredArgsConstructor
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
    public List<LocationRespDTO> getLocations(@PathVariable UUID gatheringId) {
        List<Location> location = locationService.findLocationsByGatheringId(gatheringId);
        return LocationRespDTO.fromLocations(location);
    }

    @GetMapping("/{memberId}/locations")
    @ResponseStatus(value = HttpStatus.OK)
    public List<LocationRespDTO> getLocation(@PathVariable UUID gatheringId, @PathVariable Long memberId) {
        List<Location> location = locationService.findLocationsByGatheringIdAndMemberId(gatheringId, memberId);
        return LocationRespDTO.fromLocations(location);
    }

    @GetMapping("/{memberId}/locations/{locationId}")
    @ResponseStatus(value = HttpStatus.OK)
    public LocationRespDTO getLocation(@PathVariable Long locationId) {
        Location location = locationService.findLocation(locationId);
        return LocationRespDTO.fromLocation(location);
    }
         
    @PostMapping("/{memberId}/locations")
    public ResponseEntity<Void> postLocation(@PathVariable UUID gatheringId, @PathVariable Long memberId, @RequestBody LocationReqDTO locationReqDTO, UriComponentsBuilder uriBuilder) {
        Location location = Location.builder()
                                    .name(locationReqDTO.getName())
                                    .coordinate(locationReqDTO.getCoordinate())
                                    .build();

        Location saved = locationService.addLocation(location);
        
        URI urilocation = uriBuilder.path("gatherings/{gatheringId}/members/{memberId}/location/{locationId}")
                                    .buildAndExpand(gatheringId, memberId,saved.getId())
                                    .toUri();
        return ResponseEntity.created(urilocation).build();
    }

    @PutMapping("/{memberId}/locations/{locationId}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void putLocation(@PathVariable Long locationId, @RequestBody LocationReqDTO locationReqDTO) {
        Location location = Location.builder()
                                    .id(locationId)
                                    .name(locationReqDTO.getName())
                                    .coordinate(locationReqDTO.getCoordinate())
                                    .build();        
        locationService.modifyLocation(location);
    }

    @DeleteMapping("/{memberId}/locations/{locationId}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteLocation(@PathVariable Long locationId){
        locationService.removeLocation(locationId);
    }
}
