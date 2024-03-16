package io.hexa24.yaksok.gathering.controller;

import io.hexa24.yaksok.YaksokApplication;
import io.hexa24.yaksok.gathering.domain.entity.Gathering;
import io.hexa24.yaksok.gathering.repository.GatheringRepository;
import io.hexa24.yaksok.gathering.service.GatheringService;
import io.hexa24.yaksok.gathering.service.GatheringServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@SpringBootTest(classes = YaksokApplication.class)
@AutoConfigureMockMvc
@Slf4j
class GatheringControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    private GatheringRepository gatheringRepository;

    @Test
    void getGathering() throws Exception {
        Gathering gathering = Gathering.builder()
                .name("Gathering-Test")
                .build();
        Gathering saved = gatheringRepository.save(gathering);

        log.debug(saved.toString());
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/gatherings/"+saved.getId())
                        ) // 특정 엔드포인트 호출
                .andExpect(status().isOk()); // 응답 상태가 200인지 확인
    }

    @Test
    @Transactional
    void postGathering() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/gatherings")
                        .contentType("application/json")
                        .content("{\"name\":\"testName\"}")
                ) // 특정 엔드포인트 호출
                .andExpect(status().isCreated()); // 응답 상태가 201인지 확인
    }

    @Test
    void putGathering() {
    }

    @Test
    void deleteGathering() {
    }
}