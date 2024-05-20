package com.project.lovlind.domain.chat.cache.impl;

import static com.project.lovlind.domain.chat.cache.ParticipantCache.participantMap;

import com.project.lovlind.domain.chat.cache.CacheParticipantRepository;
import com.project.lovlind.domain.chat.cache.dto.ParticipantDto;
import java.util.Map;
import java.util.Optional;
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
        .filter(p -> p.getMemberId().equals(memberId))
        .findFirst()
        .orElse(new ParticipantDto());
  }

  @Override
  public ParticipantDto findRoomIdBySessionId(String sessionId) {
    Optional<ParticipantDto> participant =
        participantMap.values().stream()
            .flatMap(roomParticipants -> roomParticipants.values().stream())
            .filter(p -> p.getSessionId().equals(sessionId))
            .findFirst();

    return participant.orElse(new ParticipantDto());
  }

  @Override
  public void updateOrSaveParticipant(ParticipantDto changeDto) {
    ParticipantDto savedData = findMemberId(changeDto.getMemberId(), changeDto.getRoomId());
    updateParticipant(changeDto, savedData);
    // save participant in cache
    save(changeDto.getRoomId(), changeDto.getSessionId(), changeDto);
  }

  @Override
  public void deleteParticipant(String sessionId) {
    participantMap.values().forEach(roomParticipants -> roomParticipants.remove(sessionId));
  }

  private void updateParticipant(ParticipantDto change, ParticipantDto target) {
    target.setSessionId(change.getSessionId());
    target.setMemberId(change.getMemberId());
    target.setNickname(change.getNickname());
    target.setChatType(change.getChatType());
    target.setRoomId(change.getRoomId());
  }
}
