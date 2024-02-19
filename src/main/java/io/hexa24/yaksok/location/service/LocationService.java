package io.hexa24.yaksok.location.service;

import java.util.List;
import java.util.UUID;

import io.hexa24.yaksok.location.domain.entity.Location;

public interface LocationService {
    List<Location> findLocationsByGatheringId(UUID gatheringId);
    List<Location> findLocationsByGatheringIdAndMemberId(UUID gatheringId, Long memberId);
    Location findLocation(Long id);
    Location addLocation(Location location);
    void modifyLocation(Location location);
    void removeLocation(Long id);
}