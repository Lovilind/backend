package com.project.lovlind.domain.chat.cache.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ParticipantDto {
  private String sessionId;
  private Long memberId;
  private String nickname;
  private String chatType;
  private Long roomId;

  public ParticipantDto(String sessionId, PrincipalDto user, String chatType, Long roomId) {
    this.sessionId = sessionId;
    this.chatType = chatType;
    this.roomId = roomId;
    this.memberId = user.getMemberId();
    this.nickname = user.getNickname();
  }
}
