package io.hexa24.yaksok.member.controller;

import java.net.URI;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import io.hexa24.yaksok.gathering.domain.entity.Gathering;
import io.hexa24.yaksok.member.domain.dto.MemberReqDTO;
import io.hexa24.yaksok.member.domain.dto.MemberRespDTO;
import io.hexa24.yaksok.member.domain.entity.Member;
import io.hexa24.yaksok.member.service.MemberServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("gatherings/{gatheringId}/members")
@RequiredArgsConstructor
public class MemberController {
    
    private final MemberServiceImpl memberService;
    
    @GetMapping("/{memberId}")
    @ResponseStatus(value = HttpStatus.OK)
    public MemberRespDTO getMember(@PathVariable Long memberId) {
        Member member = memberService.findMember(memberId);
        
        return MemberRespDTO.builder()
                            .id(member.getId())
                            .gathering(member.getGathering())
                            .name(member.getName())
                            .colour(member.getColour())
                            .build();
    }
    
    @PostMapping("")
    public ResponseEntity postMember(@PathVariable UUID gatheringId, @RequestBody @Valid MemberReqDTO memberReqDTO, UriComponentsBuilder uriBuilder) {
        Member member = Member.builder()
                                .gathering(Gathering.builder().id(gatheringId).build())
                                .name(memberReqDTO.getName())
                                .colour(memberReqDTO.getColour())
                                .build();

        Member saved = memberService.addMember(member);
        final URI location = uriBuilder.path("gatherings/{gatheringId}/members/{member_id}").buildAndExpand(gatheringId,saved.getId()).toUri();
        return ResponseEntity.created(location).build();
    }
    
    @PutMapping("/{memberId}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void putMember(@PathVariable UUID gatheringId, @PathVariable Long memberId, @RequestBody MemberReqDTO memberReqDTO) {
        Member member = Member.builder()
                                .id(memberId)
                                .name(memberReqDTO.getName())
                                .colour(memberReqDTO.getColour())
                                .build();   
        memberService.modifyMember(member);
    }

    @DeleteMapping("/{memberId}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteMember(@PathVariable Long memberId) {
        memberService.removeMember(memberId);
    }
}
