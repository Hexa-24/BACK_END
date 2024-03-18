package io.hexa24.yaksok.member.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.hexa24.yaksok.YaksokApplication;
import io.hexa24.yaksok.gathering.domain.entity.Gathering;
import io.hexa24.yaksok.gathering.repository.GatheringRepository;
import io.hexa24.yaksok.member.domain.dto.MemberSaveReqDTO;
import io.hexa24.yaksok.member.repository.MemberRepository;
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

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = YaksokApplication.class)
@AutoConfigureMockMvc
@Transactional
@Rollback
@Slf4j
class MemberControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private GatheringRepository gatheringRepository;

    @Test
    void getMembers() {
    }

    @Test
    void getMember() {
    }

    @Test
    void postMember_Test() throws Exception {
    //GIVEN
        Gathering gatheringDTO = Gathering.builder()
                .name("4/24 모임 약속")
                .build();
        Gathering gathering = gatheringRepository.save(gatheringDTO);
    // WHEN
        // 모임 생성 정보 JSON으로 변환
        MemberSaveReqDTO member1 = MemberSaveReqDTO.builder()
                .name("철수")
                .build();
        Gson gson = new GsonBuilder().create();
        String jsonPayload = gson.toJson(member1);

        // mockMvc
        mockMvc.perform(
            MockMvcRequestBuilders
                    .post("/gatherings/{gatheringId}/members",gathering.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(jsonPayload)
        )
    // THEN: Created(201) 상태코드 확인
            // location Header의 정보를 확인하고자 하는데 UUID를 DB에서 생성하여 대조할 방법이 없음
            //.andExpect(header().string("location","/gatherings/{gatheringId}/members"))
        .andExpect(status().isCreated());
    }

    @Test
    void putMember() {

    }

    @Test
    void deleteMember() {
    }
}