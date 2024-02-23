package io.hexa24.yaksok.gathering.controller;

import java.net.URI;
import java.text.ParseException;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import io.hexa24.yaksok.gathering.domain.dto.GatheringReqDTO;
import io.hexa24.yaksok.gathering.domain.dto.GatheringRespDTO;
import io.hexa24.yaksok.gathering.domain.entity.Gathering;
import io.hexa24.yaksok.gathering.service.GathringServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("gatherings")
@RequiredArgsConstructor
public class GatheringController {

    private final GathringServiceImpl gatheringService;

    
    /**
     * 식별자(UUID)를 통해 Gathering을 조회하는 GET 메서드 입니다.
     * @author HYS
     * 
     * @param gatheringId Gathering 식별자(UUID)
     * @return gatheringRespDTO 조회된 모임 정보에 대한 응답
     */
    @GetMapping("/{gatheringId}")
    @ResponseStatus(HttpStatus.OK)
    public GatheringRespDTO getGathering(@PathVariable UUID gatheringId) {
        Gathering gathering = gatheringService.findGathering(gatheringId);
        return GatheringRespDTO.builder()
                        .id(gathering.getId())
                        .name(gathering.getName())
                        .point(gathering.getPoint())
                        .build();
    }
    /**
     * 제공된 요청 DTO로 Gathering을 생성하는 POST 메서드 입니다.
     * 
     * @param gatheringReqDTO Gathering 생성에 필요한 요청 DTO
     * @param uriBuilder URI를 동적으로 생성하기 위한 UriComponentsBuilder
     * @return 생성된 Gathering의 URI를 포함한 ResponseEntity
     * @throws ParseException 파싱 예외가 발생할 경우
     */
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

    /**
     * 제공된 요청 DTO로 Gathering을 수정하는 PUT 메서드 입니다.
     * 
     * @param gatheringId RESTAPI의 명확성을 높이기 위한 변수. 비즈니스 로직에서는 사용되지 않음.
     * @param gatheringReqDTO Gathering 수정에 필요한 요청 DTO. 식별자(UUID)도 포함
     */
    @PutMapping("/{gatheringId}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void putGathering(@PathVariable UUID gatheringId, @RequestBody @Valid GatheringReqDTO gatheringReqDTO) {
 
        Gathering gathering = Gathering.builder()
                    .id(gatheringReqDTO.getId())
                    .name(gatheringReqDTO.getName())
                    .point(gatheringReqDTO.getPoint())
                    .build();
        
        gatheringService.modifyGathering(gathering);
        
    }

    /**
     * 식별자(UUID)를 통해 Gathering을 제거하는 DELETE 메서드 입니다.
     * 
     * @param gatheringId
     */
    @DeleteMapping("/{gatheringId}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteGathering(@PathVariable UUID gatheringId){
        gatheringService.removeGathering(gatheringId);
    }

}