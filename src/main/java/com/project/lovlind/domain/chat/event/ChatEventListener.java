package com.project.lovlind.domain.chat.event;

import com.project.lovlind.domain.chat.cache.CacheParticipantRepository;
import com.project.lovlind.domain.chat.cache.dto.ParticipantDto;
import com.project.lovlind.domain.chat.cache.dto.PrincipalDto;
import com.project.lovlind.domain.chat.entity.Chatroom;
import com.project.lovlind.domain.member.entity.Member;
import com.project.lovlind.domain.participaint.entity.Participant;
import com.project.lovlind.domain.participaint.repository.ParticipantRepository;
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
  private final CacheParticipantRepository repository;
  private final ParticipantRepository participantRepository;

  @EventListener
  public void onConnect(SessionConnectEvent event) {

    log.info("connection!! Sender This is ChatEventListener");
  }

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

    // save participant in cache
    ParticipantDto dto = new ParticipantDto(sessionId, user, chatType, roomId);
    repository.save(roomId, sessionId, dto);

    // save participant in RDB
    Participant participant = new Participant(new Member(user.getMemberId()), new Chatroom(roomId));
    participantRepository.save(participant);
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
