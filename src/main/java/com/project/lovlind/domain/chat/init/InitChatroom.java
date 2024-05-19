package com.project.lovlind.domain.chat.init;

import com.project.lovlind.domain.chat.cache.CacheParticipantRepository;
import com.project.lovlind.domain.chat.entity.Chatroom;
import com.project.lovlind.domain.chat.repository.ChatroomRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class InitChatroom {
  private final ChatroomRepository chatroomRepository;
  private final CacheParticipantRepository cacheParticipantRepository;

  @Scheduled(initialDelay = 10000) // 서버 시작 10초 뒤 실행
  public void saveChatroomInCache() {
    log.info("init chatroom ini cache");
    List<Chatroom> chatroom = chatroomRepository.findAll();
    chatroom.forEach(c -> cacheParticipantRepository.saveRoom(c.getId()));
  }
}
