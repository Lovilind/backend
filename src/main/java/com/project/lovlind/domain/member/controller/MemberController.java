package com.project.lovlind.domain.member.controller;

import com.project.lovlind.conmon.requset.dto.CurrentUser;
import com.project.lovlind.domain.member.controller.dto.request.PostMemberDto;
import com.project.lovlind.domain.member.entity.Member;
import com.project.lovlind.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MemberController {
  private final MemberService memberService;
  private static final String URL = "/api/members";

  @GetMapping("/sign/available")
  public Boolean infoEmail(@RequestParam String email) {
    return memberService.notExistByEmail(email);
  }

  @GetMapping("/sign/issue")
  public Boolean issueEmail(@RequestParam String email) {
    return memberService.sendEmailAndVerifyNumber(email);
  }

  @GetMapping("/sign/confirm")
  public Boolean confirmEmail(@RequestParam String email, @RequestParam String code) {
    return memberService.isSuccessVerifyEmail(email, code);
  }

  @PostMapping("/sign/members")
  public Long signIn(@RequestBody PostMemberDto dto) {
    Long savedId = memberService.save(dto);
    return savedId;
  }

  @DeleteMapping("/sign/members")
  public void signOut(CurrentUser user) {
    memberService.delete(user.getUserId());
  }

  @GetMapping("/members")
  public Member getMyInfo(CurrentUser user) {
    return memberService.getMemberById(user.getUserId());
  }
}
