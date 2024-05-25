package com.project.lovlind.domain.member.controller;

import com.project.lovlind.conmon.utils.response.UrlCreator;
import com.project.lovlind.domain.member.controller.dto.request.PostMemberDto;
import com.project.lovlind.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
public class MemberController {
  private final MemberService memberService;
  private static final String URL = "/api/members";

  @PostMapping
  public ResponseEntity<Void> save(@RequestBody PostMemberDto dto) {
    Long savedId = memberService.save(dto);
    return ResponseEntity.created(UrlCreator.createUri(URL, savedId)).build();
  }
}
