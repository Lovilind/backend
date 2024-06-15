package com.project.lovlind.domain.chat.controller.dto.request;

import com.project.lovlind.domain.chat.entity.Chatroom;
import com.project.lovlind.domain.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ChatroomReportDto {

  private String reason;
  private Member reporter;
  private Member reportedId;
  private Chatroom chatroom;
}
