package com.project.lovlind.domain.chat.event;

import static com.project.lovlind.conmon.utils.message.AbstractSubProtocolEventUtils.*;
import static com.project.lovlind.conmon.utils.message.AbstractSubProtocolEventUtils.getPrincipalDtoFromEvent;

import com.project.lovlind.domain.chat.cache.CacheParticipantRepository;
import com.project.lovlind.domain.chat.cache.dto.ParticipantDto;
import com.project.lovlind.domain.chat.cache.dto.PrincipalDto;
import com.project.lovlind.domain.participaint.service.ParticipantService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionSubscribeEvent;

@Slf4j
@Component
@RequiredArgsConstructor
public class ChatSubscribeEventListener {
  private final CacheParticipantRepository cacheRepository;
  private final ParticipantService participantService;

  @EventListener
  public void onSubscribe(SessionSubscribeEvent event) {
    // find chat roomId by destination header
    String sessionId = getSessionIdFromHeader(event);
    String chatType = getChatroomTypeFromHeader(event);
    Long roomId = getChatroomIdFromHeader(event);

    PrincipalDto user = getPrincipalDtoFromEvent(event);

    // find Participant in RDB 조회 결과 데이터가 존재하지 않는다면 저장 아니면 패스
    participantService.saveParticipantIfNotExist(user.getMemberId(), roomId);

    // find participant in cache  [잠시 웹 사이트를 껐다 다시 킨 경우 고려]
    cacheRepository.updateOrSaveParticipant(
        createParticipantDto(sessionId, user, chatType, roomId));
  }

  private ParticipantDto createParticipantDto(
      String sessionId, PrincipalDto user, String chatType, Long roomId) {
    return new ParticipantDto(sessionId, user, chatType, roomId);
  }
}
