package com.project.lovlind.domain.member.controller;

import com.project.lovlind.conmon.requset.dto.CurrentUser;
import com.project.lovlind.conmon.utils.response.UrlCreator;
import com.project.lovlind.domain.member.controller.dto.request.PostMemberDto;
import com.project.lovlind.domain.member.entity.Member;
import com.project.lovlind.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MemberController {
  private final MemberService memberService;
  private static final String URL = "/api/members";

  @GetMapping("/sign/available")
  public ResponseEntity<Boolean> infoEmail(@RequestParam String email) {
    return ResponseEntity.ok(memberService.notExistByEmail(email));
  }

  @GetMapping("/sign/issue")
  public ResponseEntity<Boolean> issueEmail(@RequestParam String email) {
    return ResponseEntity.ok(memberService.sendEmailAndVerifyNumber(email));
  }

  @GetMapping("/sign/confirm")
  public ResponseEntity<Boolean> confirmEmail(@RequestParam String email, @RequestParam String code) {
    return ResponseEntity.ok(memberService.verifyEmail(email, code));
  }

  @PostMapping("/members")
  public ResponseEntity<Void> signIn(@RequestBody PostMemberDto dto) {
    Long savedId = memberService.save(dto);
    return ResponseEntity.created(UrlCreator.createUri(URL, savedId)).build();
  }

  @DeleteMapping("/members")
  public ResponseEntity<Void> signOut(CurrentUser user) {
    memberService.delete(user.getUserId());
    return ResponseEntity.ok().build();
  }

  @GetMapping("/members")
  public ResponseEntity<Member> getMyInfo(CurrentUser user) {
    return ResponseEntity.ok(memberService.getMemberById(user.getUserId()));
  }
}
