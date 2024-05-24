package com.project.lovlind.domain.chat.service;

import static com.project.lovlind.domain.chat.exception.ChatExceptionCode.*;

import com.project.lovlind.conmon.exception.BusinessLogicException;
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
    if (!cache.checkRoomExist(roomId)) {
      throw new BusinessLogicException(NOT_FOUND_CHATROOM);
    }

    // create Entity
    ParticipantDto savedPrincipal = cache.getParticipant(roomId, sessionId);
    Message requestMessage =
        new Message(
            dto.getMessage(),
            new Member(savedPrincipal.getMemberId()),
            new Chatroom(roomId),
            dto.getMessageType());

    // save Message
    messageRepository.save(requestMessage);

    // create response dto
    return new SendMessageDto(savedPrincipal.getNickname(), dto.getMessage(), dto.getMessageType());
  }
}
