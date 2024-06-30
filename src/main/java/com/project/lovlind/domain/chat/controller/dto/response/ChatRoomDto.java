package com.project.lovlind.domain.chat.controller.dto.response;

import com.project.lovlind.domain.chat.entity.Chatroom;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class ChatRoomDto {
  private Long id;
  private String title;
  private Integer participantCount;

  public ChatRoomDto(Chatroom entity) {
    this.id = entity.getId();
    this.title = entity.getTitle();
    this.participantCount = entity.getParticipantsList().size();
  }
}
