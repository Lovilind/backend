package com.project.lovlind.domain.chat.cache.impl;

import static com.project.lovlind.domain.chat.cache.ParticipantCache.participantMap;

import com.project.lovlind.domain.chat.cache.CacheParticipantRepository;
import com.project.lovlind.domain.chat.cache.dto.ParticipantDto;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Component;

@Component
public class CacheParticipantRepositoryMapImpl implements CacheParticipantRepository {

  @Override
  public void save(Long roomId, String sessionId, ParticipantDto dto) {
    participantMap.computeIfAbsent(roomId, k -> new ConcurrentHashMap<>()).put(sessionId, dto);
  }

  @Override
  public int getParticipantCountByRoomId(Long roomId) {
    return participantMap.getOrDefault(roomId, new ConcurrentHashMap<>()).size();
  }

  @Override
  public ParticipantDto getParticipant(Long roomId, String sessionId) {
    return participantMap.getOrDefault(roomId, new ConcurrentHashMap<>()).get(sessionId);
  }

  @Override
  public boolean checkRoomExist(Long roomId) {
    return participantMap.containsKey(roomId);
  }
}
