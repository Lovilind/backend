package com.project.lovlind.domain.chat.event;

import static com.project.lovlind.conmon.utils.message.AbstractSubProtocolEventUtils.getPrincipalDtoFromEvent;

import com.project.lovlind.domain.chat.cache.CacheParticipantRepository;
import com.project.lovlind.domain.chat.cache.dto.ParticipantDto;
import com.project.lovlind.domain.chat.cache.dto.PrincipalDto;
import com.project.lovlind.domain.chat.repository.MessageRepository;
import com.project.lovlind.domain.participaint.entity.Participant;
import com.project.lovlind.domain.participaint.repository.ParticipantRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Slf4j
@Component
@RequiredArgsConstructor
public class ChatDisconnectEvent {
  private final CacheParticipantRepository cacheRepository;
  private final ParticipantRepository participantRepository;
  private final MessageRepository messageRepository;

  // 웹사이트가 꺼질 경우 마지막 데이터 조회
  @EventListener
  public void onDisconnect(SessionDisconnectEvent event) {
    PrincipalDto user = getPrincipalDtoFromEvent(event);
    String sessionId = event.getSessionId();

    // session 정보활용 -> chatroom 정보 조회 -> 마지막으로 전송한 message 조회 -> participant 저장

    ParticipantDto cachedParticipant = cacheRepository.findRoomIdBySessionId(sessionId);

    Optional<Participant> optionalParticipant =
        participantRepository.findByMemberIdAndRoomId(
            user.getMemberId(), cachedParticipant.getRoomId());

    optionalParticipant.ifPresent(
        p -> {
          Long lastSendMessage =
              messageRepository.findLastMessageByRoomId(cachedParticipant.getRoomId());
          p.addLastSendMessage(lastSendMessage);
        });

    log.info("disconnection!! GoodBy!");
  }
}
