package io.hexa24.yaksok.location.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.hexa24.yaksok.location.service.LocationServiceImpl;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("gatherings/{gatheringId}/members/{memberId}/locations")
@RequiredArgsConstructor
public class LocationController {
    
    private final LocationServiceImpl locationService;

    

}
