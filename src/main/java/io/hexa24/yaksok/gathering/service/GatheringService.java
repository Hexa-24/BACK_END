package io.hexa24.yaksok.gathering.service;

import java.util.UUID;

import io.hexa24.yaksok.gathering.domain.dto.GatheringRespDTO;
import io.hexa24.yaksok.gathering.domain.entity.Gathering;

public interface GatheringService {
    GatheringRespDTO findGathering(UUID id);
    Gathering addGathering(Gathering gathering);
    void removeGathering(UUID id);
}
