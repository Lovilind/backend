package com.project.lovlind.domain.chat.cache.impl;

import static com.project.lovlind.domain.chat.cache.ParticipantCache.participantMap;

import com.project.lovlind.domain.chat.cache.CacheParticipantRepository;
import com.project.lovlind.domain.chat.cache.dto.ParticipantDto;
import java.util.Map;
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

  @Override
  public void saveRoom(Long roomId) {
    participantMap.computeIfAbsent(roomId, k -> new ConcurrentHashMap<>());
  }

  @Override
  public ParticipantDto findMemberId(Long memberId, Long chatroomId) {
    Map<String, ParticipantDto> chatroomMap = participantMap.get(chatroomId);
    return chatroomMap.values().stream()
        .filter(p -> p.equals(memberId))
        .findFirst()
        .orElse(new ParticipantDto());
  }
}
