package io.hexa24.yaksok.gathering.controller;

import java.net.URI;
import java.text.ParseException;
import java.util.UUID;

import io.hexa24.yaksok.gathering.service.GatheringService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import io.hexa24.yaksok.gathering.domain.dto.GatheringPostReqDTO;
import io.hexa24.yaksok.gathering.domain.dto.GatheringRespDTO;
import io.hexa24.yaksok.gathering.domain.entity.Gathering;
import io.hexa24.yaksok.gathering.service.GatheringServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("gatherings")
@RequiredArgsConstructor
@Slf4j
public class GatheringController {

    private final GatheringService gatheringService;

    /**
     * 식별자(UUID)를 통해 Gathering을 조회하고 GatheringRespDTO형으로 반환하는 GET 메서드 입니다.
     * @author HYS
     * 
     * @param gatheringId Gathering 식별자(UUID)
     * @return gatheringRespDTO 조회된 모임 정보에 대한 응답
     */
    @GetMapping("/{gatheringId}")
    public ResponseEntity<GatheringRespDTO> getGathering(@PathVariable UUID gatheringId) {
        // 식별자(UUID) 값을 갖는 Gathering 조회
        GatheringRespDTO gatheringRespDTO = gatheringService.findGathering(gatheringId);
        // GatheringRespDTO를 200(OK)와 함께 반환
        return ResponseEntity.ok().body(gatheringRespDTO);
    }
    /**
     * 제공된 요청 DTO로 Gathering을 생성하는 POST 메서드 입니다.
     * @author HYS
     * 
     * @param gatheringPostReqDTO Gathering 생성에 필요한 요청 DTO
     * @param uriBuilder URI를 동적으로 생성하기 위한 UriComponentsBuilder
     * @return 생성된 Gathering의 URI를 포함한 ResponseEntity
     * @throws ParseException location 태그에 저장할 URL을 생성하는 중에 문제가 발생할 경우
     */
    @PostMapping("")
    public ResponseEntity<Void> postGathering(@RequestBody @Valid GatheringPostReqDTO gatheringPostReqDTO, UriComponentsBuilder uriBuilder) throws ParseException {

        // DTO(GatheringReqDTO)를 Entity(Gathering)로 변환
        Gathering gathering = gatheringPostReqDTO.toEntity();

        // Entity(Gathering)를 DB에 추가
        Gathering saved = gatheringService.addGathering(gathering);

        // 저장된 Entity의 EndPoint URI 생성
        final URI location = uriBuilder.path("/gatherings/{id}").buildAndExpand(saved.getId()).toUri();
        log.debug(String.valueOf(location));
        // 생성된 URI를 location 헤더에 담아 201(Created) 상태로 반환

        return ResponseEntity.created(location).build();
    }
    @GetMapping("/{gatheringId}/conclusion")
    public ResponseEntity getGatheringConclusion(@PathVariable UUID gatheringId) {
        //return ResponseEntity.ok().body(url);
        return null;
    }
}