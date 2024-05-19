package com.project.lovlind.domain.chat.controller.dto.request;

import com.project.lovlind.domain.chat.controller.enums.MessageType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MessageDto {
  private MessageType messageType;
  private Long writerId;
  private String message;
}
