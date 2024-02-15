package io.hexa24.yaksok.location.service;

import org.springframework.stereotype.Service;

import io.hexa24.yaksok.location.repository.LocationRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LocationServiceImpl implements LocationService {
    
    private final LocationRepository locationRepository;

}
