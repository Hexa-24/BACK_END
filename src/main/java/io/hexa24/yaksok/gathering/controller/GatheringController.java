package io.hexa24.yaksok.gathering.controller;

import org.springframework.web.bind.annotation.RestController;

import io.hexa24.yaksok.gathering.service.GathringService;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.RequestMapping;



@RestController
@RequestMapping("gatherings")
@RequiredArgsConstructor
public class GatheringController {

        private final GathringService gathringService;


}
