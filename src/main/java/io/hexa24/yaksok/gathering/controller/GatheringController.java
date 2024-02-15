package io.hexa24.yaksok.gathering.controller;

import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.hexa24.yaksok.gathering.domain.dto.GatheringDTO;
import io.hexa24.yaksok.gathering.domain.entity.Gathering;
import io.hexa24.yaksok.gathering.service.GathringService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("gatherings")
@RequiredArgsConstructor
public class GatheringController {

    private final GathringService gathringService;

    private final ModelMapper modelMapper;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public GatheringDTO getGathering(@PathVariable UUID id) {
        Gathering gathering = gathringService.findGathering(id); // 이 코드가 꼭 필요할지는 고민, PathVariable에서 null 값이 들어올 수 있나?
        GatheringDTO gatheringDTO = convertToDto(gathering);
        return gatheringDTO;
    }
    
    private GatheringDTO convertToDto(Gathering gathering) {
        GatheringDTO gatheringDTO = modelMapper.map(gathering, GatheringDTO.class);
        return gatheringDTO;
    }

}