package com.project.lovlind.domain.chat.controller.dto.response;

import com.project.lovlind.domain.chat.controller.enums.MessageType;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SendMessageDto implements Serializable {
  private MessageType messageType;
  private String nickname;
  private String sendTime;
  private String message;

  public SendMessageDto(String nickname, String message, MessageType messageType) {
    this.messageType = messageType;
    this.nickname = nickname;
    this.sendTime =
        LocalDateTime.now(ZoneId.of("Asia/Seoul"))
            .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    this.message = message;
  }
}
