package com.project.lovlind.domain.chat.event;

import com.project.lovlind.domain.chat.cache.CacheParticipantRepository;
import com.project.lovlind.domain.chat.cache.dto.ParticipantDto;
import com.project.lovlind.domain.chat.cache.dto.PrincipalDto;
import com.project.lovlind.domain.chat.entity.Chatroom;
import com.project.lovlind.domain.member.entity.Member;
import com.project.lovlind.domain.participaint.entity.Participant;
import com.project.lovlind.domain.participaint.repository.ParticipantRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;
import org.springframework.web.socket.messaging.SessionSubscribeEvent;
import org.springframework.web.socket.messaging.SessionUnsubscribeEvent;

@Slf4j
@Component
@RequiredArgsConstructor
public class ChatEventListener {
  private final CacheParticipantRepository cacheRepository;
  private final ParticipantRepository participantRepository;

  @EventListener
  public void onConnect(SessionConnectEvent event) {

    log.info("connection!! Sender This is ChatEventListener");
  }

  // TODO : 작업 준비 중
  @EventListener
  public void onSubscribe(SessionSubscribeEvent event) {
    String destination = (String) event.getMessage().getHeaders().get("simpDestination");
    String sessionId = (String) event.getMessage().getHeaders().get("simpSessionId");

    // find chat roomId by destination header
    String[] split = destination.split("/");
    String chatType = split[2];
    Long roomId = Long.parseLong(split[3]);

    // user
    PrincipalDto user = (PrincipalDto) event.getUser();

    // find Participant in RDB 조회 결과 데이터가 존재하지 않는다면 저장 아니면 패스
    Optional<Participant> findParticipant =
        participantRepository.findByMemberIdAndRoomId(user.getMemberId(), roomId);

    if (findParticipant.isPresent() == false) {
      participantRepository.save(
          new Participant(new Member(user.getMemberId()), new Chatroom(roomId)));
    }

    // find participant in cache  [잠시 웹 사이트를 껐다 다시 킨 경우 고려]
    ParticipantDto dto = cacheRepository.findMemberId(user.getMemberId(), roomId);
    dto.setSessionId(sessionId);
    dto.setPrincipalDto(user);
    dto.setChatType(chatType);
    dto.setRoomId(roomId);

    // save participant in cache
    cacheRepository.save(roomId, sessionId, dto);
  }

  @EventListener
  public void onUnsubscribe(SessionUnsubscribeEvent event) {

    log.info("unSubscribe!! Sender");
  }

  @EventListener
  public void onDisconnect(SessionDisconnectEvent event) {
    log.info("disconnection!! GoodBy!");
  }
}
