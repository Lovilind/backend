package com.project.lovlind.domain.chat.event;

import com.project.lovlind.domain.chat.cache.CacheParticipantRepository;
import com.project.lovlind.domain.chat.cache.dto.PrincipalDto;
import com.project.lovlind.domain.participaint.repository.ParticipantRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionUnsubscribeEvent;

@Slf4j
@Component
@RequiredArgsConstructor
public class ChatUnsubscribeEventListener {
  private final ParticipantRepository participantRepository;
  private final CacheParticipantRepository cacheRepository;

  // 본인이 직접 눌러서 나갈 경우 캐쉬 RDB에서 삭제
  @EventListener
  public void onUnsubscribe(SessionUnsubscribeEvent event) {
    String sessionId = (String) event.getMessage().getHeaders().get("simpSessionId");
    // 구독 취소 -> 여기에 들어오면 참가자 정보를 죽인다.
    PrincipalDto user = (PrincipalDto) event.getUser();
    participantRepository.deleteByMemberId(user.getMemberId());

    // cache 에서 데이터 삭제
    cacheRepository.deleteParticipant(sessionId);

    log.info("unSubscribe!! Sender");
  }
}
