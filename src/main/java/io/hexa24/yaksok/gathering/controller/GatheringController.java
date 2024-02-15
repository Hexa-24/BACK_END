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

import io.hexa24.yaksok.gathering.domain.dto.GatheringDTO;
import io.hexa24.yaksok.gathering.domain.entity.Gathering;
import io.hexa24.yaksok.gathering.service.GathringService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("gatherings")
@RequiredArgsConstructor
public class GatheringController {

    private final GathringService gatheringService;

    private final ModelMapper modelMapper;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public GatheringDTO getGathering(@PathVariable UUID id) {
        Gathering gathering = gatheringService.findGathering(id); // 이 코드가 꼭 필요할지는 고민, PathVariable에서 null 값이 들어올 수 있나?
        GatheringDTO gatheringDTO = convertToDto(gathering);
        return gatheringDTO;
    }

    @PostMapping("")
    public ResponseEntity<?> postGathering(@RequestBody GatheringDTO gatheringDTO, UriComponentsBuilder uriBuilder) throws ParseException {
        Gathering gathering = convertToEntity(gatheringDTO);

        Gathering saved = gatheringService.addGathering(gathering);

        final URI location = uriBuilder.path("/gatherings/{id}").buildAndExpand(saved.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    private GatheringDTO convertToDto(Gathering gathering) {
        GatheringDTO gatheringDTO = modelMapper.map(gathering, GatheringDTO.class);
        return gatheringDTO;
    }


    private Gathering convertToEntity(GatheringDTO gatheringDTO) throws ParseException {
        Gathering gathering = modelMapper.map(gatheringDTO, Gathering.class);
        return gathering;
    }

}