package io.hexa24.yaksok.member.controller;

import java.net.URI;
import java.util.List;
import java.util.UUID;

import lombok.extern.slf4j.Slf4j;
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
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("gatherings/{gatheringId}")
@RequiredArgsConstructor
@Slf4j
public class MemberController {
    
    private final MemberServiceImpl memberService;
    
    @GetMapping("/members")
    @ResponseStatus(value = HttpStatus.OK)
    public List<MemberRespDTO> getMemberList(@PathVariable UUID gatheringId) {
        List<Member> members = memberService.findAllMembers(gatheringId);
        log.debug(members.toString());
        return MemberRespDTO.fromMembers(members);
    }

    @GetMapping("/members/{memberId}")
    @ResponseStatus(value = HttpStatus.OK)
    public MemberRespDTO getMember(@PathVariable Long memberId) {
        Member member = memberService.findMember(memberId);
        
        return MemberRespDTO.builder()
                            .id(member.getId())
                            .name(member.getName())
                            .build();
    }
    
    @PostMapping("/members")
    public ResponseEntity<Void> postMember(@PathVariable UUID gatheringId, @RequestBody MemberReqDTO memberReqDTO, UriComponentsBuilder uriBuilder) {
        log.debug("@@@@@@@@@@@@@@@@ postMember");
        Member member = Member.builder()
                                .gathering(Gathering.builder().id(gatheringId).build())
                                .name(memberReqDTO.getName())
                                .build();
        log.debug("@@@@@@@@@@@@@@@@ addMebeer");
        Member saved = memberService.addMember(member);
        final URI location = uriBuilder.path("gatherings/{gatheringId}/members/{member_id}").buildAndExpand(gatheringId,saved.getId()).toUri();
        return ResponseEntity.created(location).build();
    }
    
    @PutMapping("/members/{memberId}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void putMember(@PathVariable UUID gatheringId, @PathVariable Long memberId, @RequestBody MemberReqDTO memberReqDTO) {
        Member member = Member.builder()
                                .id(memberId)
                                .name(memberReqDTO.getName())
                                .build();
        memberService.modifyMember(member);
    }

    @DeleteMapping("/members/{memberId}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteMember(@PathVariable Long memberId) {
        memberService.removeMember(memberId);
    }
}
