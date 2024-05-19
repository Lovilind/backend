package com.project.lovlind.domain.chat.controller;

import com.project.lovlind.conmon.requset.dto.CurrentUser;
import com.project.lovlind.conmon.requset.dto.SliceResponse;
import com.project.lovlind.domain.chat.controller.dto.response.MessageListDto;
import com.project.lovlind.domain.chat.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/messages")
@RequiredArgsConstructor
public class MessageController {
  private final MessageService service;

  @GetMapping
  public ResponseEntity<SliceResponse<MessageListDto>> getMessageList(
      Pageable pageable, CurrentUser user, @RequestParam("roomId") Long roomId) {
    var result = service.findMessageSender(pageable, user.getUserId(), roomId);
    return ResponseEntity.ok(result);
  }
}
