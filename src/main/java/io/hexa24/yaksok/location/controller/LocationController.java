package io.hexa24.yaksok.location.controller;

import java.net.URI;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import io.hexa24.yaksok.location.domain.dto.LocationReqDTO;
import io.hexa24.yaksok.location.domain.entity.Location;
import io.hexa24.yaksok.location.service.LocationServiceImpl;
import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("gatherings/{gatheringId}/members/{memberId}/locations")
@RequiredArgsConstructor
public class LocationController {
    
    private final LocationServiceImpl locationService;

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
}
