package io.hexa24.yaksok.location.controller;

import io.hexa24.yaksok.YaksokApplication;
import io.hexa24.yaksok.gathering.repository.GatheringRepository;
import io.hexa24.yaksok.location.repository.LocationRepository;
import io.hexa24.yaksok.location.service.LocationServiceImpl;
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
class LocationControllerTest {

    @Autowired
    MockMvc mockMvc;

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
    void postLocation() {
    }

    @Test
    void putLocation() {
    }

    @Test
    void deleteLocation() {
    }
}