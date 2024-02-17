package io.hexa24.yaksok.gathering.service;

import java.util.UUID;

import io.hexa24.yaksok.gathering.domain.entity.Gathering;

public interface GatheringService {
    Gathering findGathering(UUID id);
    Gathering addGathering(Gathering gathering);
    void modifyGathering(Gathering gathering);
    void removeGathering(UUID id);
}
