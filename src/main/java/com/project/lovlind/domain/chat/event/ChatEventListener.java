package com.project.lovlind.domain.chat.event;

import com.project.lovlind.domain.chat.cache.CacheParticipantRepository;
import com.project.lovlind.domain.chat.cache.dto.ParticipantDto;
import com.project.lovlind.domain.chat.cache.dto.PrincipalDto;
import com.project.lovlind.domain.chat.entity.Chatroom;
import com.project.lovlind.domain.chat.repository.MessageRepository;
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
  private final MessageRepository messageRepository;

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

  // 웹사이트가 꺼질 경우 마지막 데이터 조회
  @EventListener
  public void onDisconnect(SessionDisconnectEvent event) {
    PrincipalDto user = (PrincipalDto) event.getUser();
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
