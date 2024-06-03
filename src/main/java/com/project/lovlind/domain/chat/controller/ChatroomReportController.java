package com.project.lovlind.domain.chat.controller;

import com.project.lovlind.domain.chat.controller.dto.request.ChatroomReportDto;
import com.project.lovlind.domain.chat.service.ChatroomReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/report")
@RequiredArgsConstructor
public class ChatroomReportController {

  private final ChatroomReportService service;

  /** 신고하기 */
  @PostMapping
  public ResponseEntity<Void> reportChatroom(@RequestBody ChatroomReportDto dto) {

    return ResponseEntity.ok().build();
  }
}
