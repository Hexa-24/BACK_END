package io.hexa24.yaksok.member.controller;

import org.springframework.web.bind.annotation.RestController;

import io.hexa24.yaksok.member.service.MemberServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {
    
    private final MemberServiceImpl memberService;
    
    

}
