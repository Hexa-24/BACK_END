package io.hexa24.yaksok.member.controller;

import io.hexa24.yaksok.YaksokApplication;
import io.hexa24.yaksok.location.repository.LocationRepository;
import io.hexa24.yaksok.member.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest(classes = YaksokApplication.class)
@AutoConfigureMockMvc
@Slf4j
class MemberControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    private MemberRepository memberRepository;

    @Test
    void getMembers() {
    }

    @Test
    void getMember() {
    }

    @Test
    void postMember() {



    }

    @Test
    void putMember() {
    }

    @Test
    void deleteMember() {
    }
}