package io.hexa24.yaksok.gathering.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.hexa24.gson.LocalDateAdapter;
import io.hexa24.yaksok.YaksokApplication;
import io.hexa24.yaksok.gathering.domain.dto.GatheringPostReqDTO;
import io.hexa24.yaksok.gathering.domain.entity.Gathering;
import io.hexa24.yaksok.gathering.repository.GatheringRepository;
import io.hexa24.yaksok.location.domain.dto.VenueDTO;
import io.hexa24.yaksok.location.domain.value.Address;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = YaksokApplication.class)
@AutoConfigureMockMvc
@Transactional
@Rollback
@Slf4j
class GatheringControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    private GatheringRepository gatheringRepository;

    /**
     * 모임을 생성 API를 테스트 코드
     *
     * 모입 생성에 필요한 모임 정보 및 약속장소 정보를 JSON 타입으로 입력 받은 후
     * Created(201) 상태코드와 생성 된 Location Header 정보를 반환합니다.
     *
     * @throws Exception
     */
    @Test
    void postGathering() throws Exception {
    // GIVEN: DTO 객체 생성
        GatheringPostReqDTO gatheringPostReqDTO = GatheringPostReqDTO.builder()
                .name("Hexa-24 점심 약속")
                .date(LocalDate.of(2024, 3, 18))
                .venue(
                        VenueDTO.builder()
                                .name("아라베스크")
                                .address(
                                        Address.builder()
                                                .zipcode("04349")
                                                .address1("서울 용산구")
                                                .address2("이태원로 227")
                                                .build()
                                )
                                .build()
                )
                .build();

        // 모임 생성 정보 JSON으로 변환
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .create();
        String jsonPayload = gson.toJson(gatheringPostReqDTO);

    // WHEN: Mock으로 모임 정보를 생성하는 json 정보를 전달인
        mockMvc.perform(
                    MockMvcRequestBuilders
                        .post("/gatherings")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonPayload)
                        )

    // THEN: Created(201) 상태코드 확인
                //.andExpect(header().string("location",))    // location Header의 정보를 확인하고자 하는데 UUID를 DB에서 생성하여 대조할 방법이 없음
                .andExpect(status().isCreated());
    }

    @Test
    void getGathering() throws Exception {
    // GIVEN
        Gathering gathering = Gathering.builder()
                                        .name("4/24 모임 약속")
                                        .build();
        Gathering saved = gatheringRepository.save(gathering);

    // WHEN
        mockMvc.perform(MockMvcRequestBuilders
                            .get("/gatherings/"+saved.getId())
                )
    // THEN
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))           //
                .andExpect(status().isOk())                                             // 응답 상태가 200인지 확인
                .andExpect(jsonPath("$.name").value(gathering.getName()));    //
    }

}