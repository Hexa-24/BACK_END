package io.hexa24.yaksok.location.controller;

import java.net.URI;
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
@RequestMapping("gatherings/{gatheringId}/members/{memberId}/locations")
@RequiredArgsConstructor
public class LocationController {
    
    private final LocationServiceImpl locationService;
    
    @GetMapping("/{locationId}")
    @ResponseStatus(value = HttpStatus.OK)
    public LocationRespDTO getLocation(@PathVariable Long locationId) {
        Location location = locationService.findLocation(locationId);
        LocationRespDTO locationRespDTO = LocationRespDTO.builder()
                                                            .id(location.getId())
                                                            .gathering(location.getGathering())
                                                            .member(location.getMember())
                                                            .name(location.getName())
                                                            .point(location.getPoint())
                                                            .build();
        return locationRespDTO;
    }
    
    
    @PostMapping("")
    public ResponseEntity postLocation(@PathVariable UUID gatheringId, @PathVariable Long memberId, @RequestBody LocationReqDTO locationReqDTO, UriComponentsBuilder uriBuilder) {
        Location location = Location.builder()
                                    .name(locationReqDTO.getName())
                                    .point(locationReqDTO.getPoint())
                                    .build();

        Location saved = locationService.addLocation(location);
        
        URI urilocation = uriBuilder.path("gatherings/{gatheringId}/members/{memberId}/location/{locationId}")
                                    .buildAndExpand(gatheringId, memberId,saved.getId())
                                    .toUri();
        return ResponseEntity.created(urilocation).build();
    }

    @PutMapping("/{locationId}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void putLocation(@PathVariable Long locationId, @RequestBody LocationReqDTO locationReqDTO) {
        Location location = Location.builder()
                                    .id(locationId)
                                    .name(locationReqDTO.getName())
                                    .point(locationReqDTO.getPoint())
                                    .build();        
        locationService.modifyLocation(location);
    }

    @DeleteMapping("/{locationId}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteLocation(@PathVariable Long locationId){
        locationService.removeLocation(locationId);
    }
}
