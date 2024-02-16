package io.hexa24.yaksok.member.controller;

import java.net.URI;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import io.hexa24.yaksok.member.domain.dto.MemberReqDTO;
import io.hexa24.yaksok.member.domain.dto.MemberRespDTO;
import io.hexa24.yaksok.member.domain.entity.Member;
import io.hexa24.yaksok.member.service.MemberServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;





@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {
    
    private final MemberServiceImpl memberService;
    
    @GetMapping("")
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
    
    @PostMapping("")
    public ResponseEntity postMethodName(@RequestBody @Valid MemberReqDTO memberReqDTO, UriComponentsBuilder uriBuilder) {
        Member member = Member.builder()
                                .id(memberReqDTO.getId())
                                .gathering_id(memberReqDTO.getGathering_id())
                                .name(memberReqDTO.getName())
                                .colour(memberReqDTO.getColour())
                                .build();

        Member saved = memberService.addMember(member);
        final URI location = uriBuilder.path("/members/{id}").buildAndExpand(saved.getId()).toUri();
        return ResponseEntity.created(location).build();
    }
    
}
