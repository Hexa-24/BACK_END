package io.hexa24.yaksok.location.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.hexa24.yaksok.YaksokApplication;
import io.hexa24.yaksok.gathering.domain.entity.Gathering;
import io.hexa24.yaksok.gathering.repository.GatheringRepository;
import io.hexa24.yaksok.location.domain.dto.AddressDTO;
import io.hexa24.yaksok.location.domain.dto.CoordinateDTO;
import io.hexa24.yaksok.location.domain.dto.LocationReqDTO;
import io.hexa24.yaksok.location.domain.entity.Location;
import io.hexa24.yaksok.location.domain.value.Address;
import io.hexa24.yaksok.location.domain.value.Coordinate;
import io.hexa24.yaksok.location.repository.LocationRepository;
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

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = YaksokApplication.class)
@AutoConfigureMockMvc
@Transactional
@Slf4j
class LocationControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    private GatheringRepository gatheringRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private LocationRepository locationRepository;

    @Test
    void getLocations() {

    }

    @Test
    void getLocation() {
    }

    @Test
    void testGetLocation() {
    }

    @Test
    void postLocation_With_NoExistingLocation_TEST() throws Exception {
    // GIVEN: DTO 객체 생성
        Gathering gathering = Gathering.builder()
                .name("4/24 모임 약속")
                .build();
        Gathering gatheringSaved = gatheringRepository.save(gathering);

        Member member = Member.builder()
                .gathering(gatheringSaved)
                .name("맹구")
                .build();
        Member memberSaved = memberRepository.save(member);

        Location location = Location.builder()
                .name("신당동 떡볶이")
                .address(Address.builder()
                        .zipcode("04611")
                        .address1("서울 중구")
                        .address2("퇴계로76길 50 아이러브떡볶이")
                        .build()
                )
                .coordinate(new Coordinate(33,22)

                )
                .build();
        Location locationSaved = locationRepository.save(location);

    // WHEN: Mock으로 모임 정보를 생성하는 json 정보를 전달인
        LocationReqDTO locationReqDTO = LocationReqDTO.builder()
                .name("스타벅스 동대입구역점")
                .address(AddressDTO.builder()
                        .zipcode("04606")
                        .address1("서울 중구")
                        .address2("장충단로 166")
                        .build()
                )
                .coordinate(new CoordinateDTO(54, 34)
                )
                .build();
        Gson gson = new GsonBuilder().create();
        String jsonPayload = gson.toJson(locationReqDTO);
        log.debug(jsonPayload);
        mockMvc.perform(
                        MockMvcRequestBuilders
                                .post("/gatherings/{gatheringId}/members/{memberId}/locations",gatheringSaved.getId(),memberSaved.getId())
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonPayload)
                )
    // THEN: Created(201) 상태코드 확인
                //.andExpect(header().string("location",))    // location Header의 정보를 확인하고자 하는데 UUID를 DB에서 생성하여 대조할 방법이 없음
                .andExpect(status().isCreated());
    }
    @Test
    void postLocation_With_ExistingLocation_TEST() throws Exception {
        // GIVEN: DTO 객체 생성
        Gathering gathering = Gathering.builder()
                .name("4/24 모임 약속")
                .build();
        Gathering gatheringSaved = gatheringRepository.save(gathering);

        Member member = Member.builder()
                .gathering(gatheringSaved)
                .name("맹구")
                .build();
        Member memberSaved = memberRepository.save(member);

        Location location = Location.builder()
                .name("신당동 떡볶이")
                .address(Address.builder()
                        .zipcode("04611")
                        .address1("서울 중구")
                        .address2("퇴계로76길 50 아이러브떡볶이")
                        .build()
                )
                .coordinate(new Coordinate(33,22)

                )
                .build();
        Location locationSaved = locationRepository.save(location);

        // WHEN: Mock으로 모임 정보를 생성하는 json 정보를 전달인
        LocationReqDTO locationReqDTO = LocationReqDTO.builder()
                .name("신당동 떡볶이")
                .address(AddressDTO.builder()
                        .zipcode("04611")
                        .address1("서울 중구")
                        .address2("퇴계로76길 50 아이러브떡볶이")
                        .build()
                )
                .coordinate(new CoordinateDTO(33, 22)
                )
                .build();
        Gson gson = new GsonBuilder().create();
        String jsonPayload = gson.toJson(locationReqDTO);
        log.debug(jsonPayload);
        mockMvc.perform(
                        MockMvcRequestBuilders
                                .post("/gatherings/{gatheringId}/members/{memberId}/locations",gatheringSaved.getId(),memberSaved.getId())
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonPayload)
                )
                // THEN: Created(201) 상태코드 확인
                //.andExpect(header().string("location",))    // location Header의 정보를 확인하고자 하는데 UUID를 DB에서 생성하여 대조할 방법이 없음
                .andExpect(status().isCreated());
    }
    @Test
    void putLocation() {
    }

    @Test
    void deleteLocation() {
    }
}