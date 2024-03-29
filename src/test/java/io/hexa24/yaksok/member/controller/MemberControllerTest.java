package io.hexa24.yaksok.member.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.hexa24.yaksok.YaksokApplication;
import io.hexa24.yaksok.gathering.domain.entity.Gathering;
import io.hexa24.yaksok.gathering.repository.GatheringRepository;
import io.hexa24.yaksok.location.domain.entity.Location;
import io.hexa24.yaksok.location.domain.value.Address;
import io.hexa24.yaksok.location.domain.value.Coordinate;
import io.hexa24.yaksok.member.domain.dto.MemberReqDTO;
import io.hexa24.yaksok.member.domain.entity.Member;
import io.hexa24.yaksok.member.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = YaksokApplication.class)
@AutoConfigureMockMvc
@Transactional
@Slf4j
class MemberControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private GatheringRepository gatheringRepository;

    @Test
    void getMembers_Test() throws Exception {
        // GIVEN
        Gathering gathering = Gathering.builder()
                .name("4/24 모임 약속")
                .build();
        Gathering gatheringSaved = gatheringRepository.save(gathering);

        Member member1 = Member.builder()
                .gathering(gatheringSaved)
                .name("맹구")
                .build();
        Member memberSaved1 = memberRepository.save(member1);

        Member member2 = Member.builder()
                .gathering(gatheringSaved)
                .name("짱구")
                .build();
        Member memberSaved2 = memberRepository.save(member2);

        // WHEN
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/gatherings/"+gatheringSaved.getId()+"/members")
                )
        // THEN
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))           //
                .andExpect(status().isOk())                                             // 응답 상태가 200인지 확인
                .andExpect(jsonPath("$.[0].name").value(memberSaved1.getName()))   //
                .andExpect(jsonPath("$.[1].name").value(memberSaved2.getName()));   //
    }


    @Test
    void getMember_Test() throws Exception {
        // GIVEN
        Gathering gathering = Gathering.builder()
                .name("4/24 모임 약속")
                .venue(
                        Location.builder()
                                .name("신당역")
                                .address(Address.builder()
                                        .address1("서울 중구")
                                        .address2("퇴계로 431-1")
                                        .build()
                                )
                                .coordinate(new Coordinate(21,50))
                                .build()
                )
                .build();
        Gathering gatheringSaved = gatheringRepository.save(gathering);

        Member member = Member.builder()
                .gathering(gatheringSaved)
                .name("맹구")
                .build();
        Member memberSaved = memberRepository.save(member);
        // WHEN
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/gatherings/"+gatheringSaved.getId()+"/members/"+memberSaved.getId())
                )
                // THEN
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))           //
                .andExpect(status().isOk())                                             // 응답 상태가 200인지 확인
                .andExpect(jsonPath("$.name").value(memberSaved.getName()));   //
    }

    @Test
    void postMember_Test() throws Exception {
    //GIVEN
        Gathering gathering = Gathering.builder()
                .name("4/24 모임 약속")
                .venue(
                        Location.builder()
                                .name("신당역")
                                .address(Address.builder()
                                        .address1("서울 중구")
                                        .address2("퇴계로 431-1")
                                        .build()
                                )
                                .coordinate(new Coordinate(21,50))
                                .build()
                )
                .build();
        Gathering gatheringSaved = gatheringRepository.save(gathering);

        // WHEN
        // 모임 생성 정보 JSON으로 변환
        MemberReqDTO member1 = MemberReqDTO.builder()
                .name("맹구")
                .build();
        Gson gson = new GsonBuilder().create();
        String jsonPayload = gson.toJson(member1);

        // mockMvc
        mockMvc.perform(
            MockMvcRequestBuilders
                    .post("/gatherings/{gatheringId}/members",gatheringSaved.getId())
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