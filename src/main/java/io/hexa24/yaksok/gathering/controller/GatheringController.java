package io.hexa24.yaksok.gathering.controller;

import java.net.URI;
import java.text.ParseException;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import io.hexa24.yaksok.gathering.domain.dto.GatheringReqDTO;
import io.hexa24.yaksok.gathering.domain.dto.GatheringRespDTO;
import io.hexa24.yaksok.gathering.domain.entity.Gathering;
import io.hexa24.yaksok.gathering.service.GathringService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Slf4j
@RestController
@RequestMapping("gatherings")
@RequiredArgsConstructor
public class GatheringController {

    private final GathringService gatheringService;

    private final ModelMapper modelMapper;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public GatheringRespDTO getGathering(@PathVariable UUID id) {
        Gathering gathering = gatheringService.findGathering(id);
        GatheringRespDTO gatheringRespDTO = convertToRespDto(gathering);
        return gatheringRespDTO;
    }

    @PostMapping("")
    public ResponseEntity postGathering(@RequestBody @Valid GatheringReqDTO gatheringReqDTO, UriComponentsBuilder uriBuilder) throws ParseException {
        
        Gathering gathering = Gathering.builder()
                    .id(gatheringReqDTO.getId())
                    .name(gatheringReqDTO.getName())
                    .point(gatheringReqDTO.getPoint())
                    .build();

        Gathering saved = gatheringService.addGathering(gathering);

        final URI location = uriBuilder.path("/gatherings/{id}").buildAndExpand(saved.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    private GatheringRespDTO convertToRespDto(Gathering gathering) {
        GatheringRespDTO gatheringRespDTO = modelMapper.map(gathering, GatheringRespDTO.class);
        return gatheringRespDTO;
    }

}