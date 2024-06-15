package com.project.lovlind.domain.member.controller;

import com.project.lovlind.conmon.security.dto.request.LoginDto;
import com.project.lovlind.conmon.utils.response.UrlCreator;
import com.project.lovlind.domain.member.controller.dto.request.PostMemberDto;
import com.project.lovlind.domain.member.entity.Member;
import com.project.lovlind.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
public class MemberController {
  private final MemberService memberService;
  private static final String URL = "/api/members";

  @PostMapping
  public ResponseEntity<Void> signIn(@RequestBody PostMemberDto dto) {
    Long savedId = memberService.save(dto);
    return ResponseEntity.created(UrlCreator.createUri(URL, savedId)).build();
  }

  @DeleteMapping
  public ResponseEntity<Void> signOut() {
    memberService.delete(1L);
    return ResponseEntity.ok().build();
  }

  @GetMapping
  public ResponseEntity<Member> getMyInfo() {
    return ResponseEntity.ok(memberService.getMemberById(1L));
  }



}
