package com.project.lovlind.domain.chat.service;

import com.project.lovlind.domain.chat.cache.CacheParticipantRepository;
import com.project.lovlind.domain.chat.cache.dto.ParticipantDto;
import com.project.lovlind.domain.chat.controller.dto.request.MessageDto;
import com.project.lovlind.domain.chat.controller.dto.response.SendMessageDto;
import com.project.lovlind.domain.chat.entity.Chatroom;
import com.project.lovlind.domain.chat.entity.Message;
import com.project.lovlind.domain.chat.repository.MessageRepository;
import com.project.lovlind.domain.member.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatroomWebSocketService {
  private final MessageRepository messageRepository;
  private final CacheParticipantRepository cache;

  public SendMessageDto saveMessage(MessageDto dto, Long roomId, String sessionId) {

    // 활성화 되어 있는 채팅방 여부 체크
//    if (!cache.checkRoomExist(roomId)) {
//      throw new RuntimeException("활성화 된 채팅방이 존재하지 않습니다.");
//    }
    //TODO: 서비스 구현 완료 이후 추가 작업 필요(활성화된 chatroom을 모두 cache에 넣어주는 작업 이후 작업이 필요함)

    // create Entity
    ParticipantDto savedPrincipal = cache.getParticipant(roomId, sessionId);
    Message requestMessage =
        new Message(
            dto.getMessage(), new Member(savedPrincipal.getMemberId()), new Chatroom(roomId));

    // save Message
    messageRepository.save(requestMessage);

    // create response dto
    return new SendMessageDto(savedPrincipal.getNickname(), dto.getMessage(), dto.getMessageType());
  }
}
