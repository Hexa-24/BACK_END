package io.hexa24.yaksok.gathering.controller;

import org.springframework.web.bind.annotation.RestController;

import io.hexa24.yaksok.gathering.domain.dto.GatheringDTO;
import io.hexa24.yaksok.gathering.domain.entity.Gathering;
import io.hexa24.yaksok.gathering.service.GathringService;
import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Optional;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("gatherings")
@RequiredArgsConstructor
public class GatheringController {

    private final GathringService gathringService;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public GatheringDTO getGathering(@PathVariable UUID id) {
        Gathering gathering = gathringService.findGathering(id); // 이 코드가 꼭 필요할지는 고민, PathVariable에서 null 값이 들어올 수 있나?
        GatheringDTO gatheringDTO = GatheringDTO.builder()
                                                .id(gathering.getId())
                                                .name(gathering.getName())
                                                .point(gathering.getPoint())
                                                .build();
        return gatheringDTO;
    }
    
}
