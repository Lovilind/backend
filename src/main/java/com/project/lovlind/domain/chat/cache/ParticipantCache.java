package com.project.lovlind.domain.chat.cache;

import com.project.lovlind.domain.chat.cache.dto.ParticipantDto;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

// TODO : 추 후 Redis 설정 시 Redis 로 변경할 예정
public class ParticipantCache {
  public static final Map<Long, Map<String, ParticipantDto>> participantMap =
      new ConcurrentHashMap<>();
}
