package com.project.lovlind.domain.chat.service;

import static com.project.lovlind.domain.chat.exception.ChatExceptionCode.*;
import static com.project.lovlind.domain.participaint.exception.ParticipantExceptionCode.NOT_FOUND_PARTICIPANT;

import com.project.lovlind.conmon.exception.BusinessLogicException;
import com.project.lovlind.conmon.requset.dto.SliceInfo;
import com.project.lovlind.conmon.requset.dto.SliceResponse;
import com.project.lovlind.domain.chat.cache.CacheParticipantRepository;
import com.project.lovlind.domain.chat.controller.dto.response.MessageListDto;
import com.project.lovlind.domain.chat.entity.Message;
import com.project.lovlind.domain.chat.repository.ChatroomRepository;
import com.project.lovlind.domain.chat.repository.MessageRepository;
import com.project.lovlind.domain.participaint.entity.Participant;
import com.project.lovlind.domain.participaint.repository.ParticipantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MessageService {
  private final MessageRepository messageRepository;
  private final ParticipantRepository participantRepository;
  private final ChatroomRepository chatroomRepository;
  private final CacheParticipantRepository cacheParticipantRepository;

  public SliceResponse<MessageListDto> findMessageSender(
      Pageable pageable, Long memberId, Long roomId) {
    // 참가 번호 조회
    if (!cacheParticipantRepository.checkRoomExist(roomId)) {
      // 마지막 (캐시에 없을 경우 마지막으로 확인하는 용도)
      chatroomRepository
          .findById(roomId)
          .orElseThrow(() -> new BusinessLogicException(NOT_FOUND_CHATROOM));
    }

    // 참가자 정보 조회
    Participant participant =
        participantRepository
            .findByMemberIdAndRoomId(memberId, roomId)
            .orElseThrow(() -> new BusinessLogicException(NOT_FOUND_PARTICIPANT));

    // 읽기 데이터 조회
    Slice<Message> findEntitySlice = messageRepository.findMessageByRoomId(pageable, roomId);

    // 안 읽은 메시지 데이터 조회
    Long unReadMessageCount =
        messageRepository.countUnReadMessage(roomId, participant.getLastSendMessage());

    MessageListDto responseMessageList =
        new MessageListDto(
            findEntitySlice.getContent(),
            participant.getLastSendMessage(),
            roomId,
            unReadMessageCount);

    // SliceInfo 데이터 Trans
    SliceInfo sliceInfo = new SliceInfo(findEntitySlice.hasNext());

    // Response 데이터 Trans & return
    return new SliceResponse<>(
        responseMessageList, sliceInfo, String.valueOf(HttpStatus.OK.value()));
  }
}
