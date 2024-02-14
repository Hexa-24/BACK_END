package io.hexa24.yaksok.gathering.service;

import org.springframework.stereotype.Service;

import io.hexa24.yaksok.gathering.repository.GatheringRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GathringService {
    
        private final GatheringRepository gatheringRepository;

}
