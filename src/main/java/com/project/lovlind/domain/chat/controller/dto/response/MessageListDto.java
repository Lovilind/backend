package com.project.lovlind.domain.chat.controller.dto.response;

import com.project.lovlind.domain.chat.controller.enums.MessageType;
import com.project.lovlind.domain.chat.entity.Message;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class MessageListDto {
  private List<MessageDto> messageList;
  private Long lastSendMessageId;
  private Long chatroomId;
  private Long unreadMessageCount;

  public MessageListDto(
      List<Message> messageList, Long lastSendMessageId, Long chatroomId, Long unreadMessageCount) {
    this.messageList = messageList.stream().map(MessageDto::new).toList();
    this.lastSendMessageId = lastSendMessageId;
    this.chatroomId = chatroomId;
    this.unreadMessageCount = unreadMessageCount;
  }

  @NoArgsConstructor
  @AllArgsConstructor
  @Getter
  public static class MessageDto {
    private Long messageId;
    private Long memberId;
    private String nickname;
    private String message;
    private String sendTime;
    private MessageType messageType;

    public MessageDto(Message entity) {
      this.messageId = entity.getId();
      this.memberId = entity.getMember().getId();
      this.nickname = entity.getMember().getNickname();
      this.message = entity.getMessage();
      this.sendTime =
          LocalDateTime.now(ZoneId.of("Asia/Seoul"))
              .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
      this.messageType = entity.getMessageType();
    }
  }
}
