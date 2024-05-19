package com.project.lovlind.domain.chat.cache;

import com.project.lovlind.domain.chat.cache.dto.ParticipantDto;

public interface CacheParticipantRepository {
  void save(Long roomId, String sessionId, ParticipantDto dto);

  int getParticipantCountByRoomId(Long roomId);

  ParticipantDto getParticipant(Long roomId, String sessionId);

  boolean checkRoomExist(Long roomId);

  void saveRoom(Long roomId);
}
