package io.hexa24.yaksok.member.controller;

import java.net.URI;
import java.util.List;
import java.util.UUID;

import io.hexa24.yaksok.member.domain.dto.MemberDetailRespDTO;
import io.hexa24.yaksok.member.service.MemberService;
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
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("gatherings/{gatheringId}")
@RequiredArgsConstructor
@Slf4j
public class MemberController {
    
    private final MemberService memberService;

    /**
     * 그룹내 사용자 전체 조회
     *
     * @param gatheringId
     * @return
     */
    @GetMapping("/members")
    @ResponseStatus(value = HttpStatus.OK)
    public List<MemberRespDTO> getMemberList(@PathVariable UUID gatheringId) {
        List<Member> members = memberService.findAllMembers(gatheringId);
        return MemberRespDTO.convertToDTO(members);
    }

    /**
     * 그룹내 특정 사용자의 장소 단건 조회 (햄버거 버튼/내 선택보기)
     *
     * @param gatheringId
     * @param memberId
     * @return
     */
    @GetMapping("/members/{memberId}")
    @ResponseStatus(value = HttpStatus.OK)
    public MemberDetailRespDTO getLocation(@PathVariable UUID gatheringId, @PathVariable Long memberId) {
        Member member = memberService.findMemberByGatheringIdAndMemberId(gatheringId, memberId);
        return MemberDetailRespDTO.convertToDTO(member);
    }

    @PostMapping("/members")
    public ResponseEntity<Void> postMember(@PathVariable UUID gatheringId, @RequestBody MemberReqDTO memberReqDTO, UriComponentsBuilder uriBuilder) {
        Member member = Member.builder()
                                .gathering(Gathering.builder().id(gatheringId).build())
                                .name(memberReqDTO.getName())
                                .build();
        Member saved = memberService.addMember(member);
        final URI urlLocation = uriBuilder.path("gatherings/{gatheringId}/members/{member_id}").buildAndExpand(gatheringId,saved.getId()).toUri();
        return ResponseEntity.created(urlLocation).build();
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

}
