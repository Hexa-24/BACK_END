package io.hexa24.yaksok.location.service;

import io.hexa24.yaksok.location.domain.entity.Location;

public interface LocationService {
    Location findLocation(Long id)
    Location addLocation(Location location)
    void modifyLocation(Location location)
    void removeLocation(Long id)
}