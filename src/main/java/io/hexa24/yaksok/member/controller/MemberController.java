package io.hexa24.yaksok.member.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.hexa24.yaksok.member.domain.dto.MemberRespDTO;
import io.hexa24.yaksok.member.domain.entity.Member;
import io.hexa24.yaksok.member.service.MemberServiceImpl;
import lombok.RequiredArgsConstructor;




@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {
    
    private final MemberServiceImpl memberService;
    
    @GetMapping("/")
    @ResponseStatus(value = HttpStatus.OK)
    public MemberRespDTO getMember(@PathVariable Long id) {
        Member member = memberService.findMember(id);
        
        return MemberRespDTO.builder()
                            .id(member.getId())
                            .gathering_id(member.getGathering_id())
                            .name(member.getName())
                            .colour(member.getColour())
                            .build();
    }
    
    
}
