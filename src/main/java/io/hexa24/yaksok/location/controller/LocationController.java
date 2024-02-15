package io.hexa24.yaksok.location.controller;

import org.springframework.web.bind.annotation.RestController;

import io.hexa24.yaksok.location.service.LocationServiceImpl;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class LocationController {
    
    private final LocationServiceImpl locationService;

    

}
