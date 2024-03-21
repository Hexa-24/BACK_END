package io.hexa24.yaksok.location.service;

import java.util.List;
import java.util.UUID;

import io.hexa24.yaksok.location.domain.dto.LocationReqDTO;
import io.hexa24.yaksok.location.domain.entity.Candidate;
import io.hexa24.yaksok.location.domain.entity.Location;

public interface LocationService {
    List<Candidate> findLocationsByGatheringId(UUID gatheringId);
    List<Candidate> findLocationsByGatheringIdAndMemberId(UUID gatheringId, Long memberId);
    Candidate findLocation(Long id);
    Location addLocation(UUID gatheringId, Long memberId, LocationReqDTO locationReqDTO);
    void modifyLocation(Location location);
    void removeLocation(Long id);
}