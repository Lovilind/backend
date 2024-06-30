package com.project.lovlind.domain.chat.service;

import static com.project.lovlind.domain.chat.exception.ChatExceptionCode.NOT_FOUND_PARTICIPANT;

import com.project.lovlind.conmon.exception.BusinessLogicException;
import com.project.lovlind.domain.chat.cache.CacheParticipantRepository;
import com.project.lovlind.domain.chat.controller.dto.ChatrommSearchFilter;
import com.project.lovlind.domain.chat.controller.dto.request.PostChatDto;
import com.project.lovlind.domain.chat.controller.dto.response.ChatRoomDto;
import com.project.lovlind.domain.chat.entity.Chatroom;
import com.project.lovlind.domain.chat.entity.ChatroomHobby;
import com.project.lovlind.domain.chat.repository.ChatroomRepository;
import com.project.lovlind.domain.participaint.entity.Participant;
import com.project.lovlind.domain.participaint.repository.ParticipantRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatroomService {

  private final ChatroomRepository repository;
  private final CacheParticipantRepository cacheParticipantRepository;
  private final ParticipantRepository participantRepository;

  //    private final MemberRepository memberRepository;
  /*

      public void joinChatroom(Long roomId, Long userId) {
          Chatroom chatroom = repository.findById(roomId)
                  .orElseThrow(() -> new BusinessLogicException(NOT_FOUND_CHATROOM));

          Member member = memberRepository.findById(memberId)
                  .orElseThrow(() -> new BusinessLogicException(NOT_FOUND_PARTICIPANT));

          Participant participant = new Participant(member, chatroom);
          participantRepository.save(participant);
      }
  */

  public void leaveChatroom(Long roomId, Long userId) {
    Participant participant =
        participantRepository
            .findByMemberIdAndRoomId(userId, roomId)
            .orElseThrow(() -> new BusinessLogicException(NOT_FOUND_PARTICIPANT));

    participantRepository.delete(participant);
  }

  public List<ChatRoomDto> getChatrooms(ChatrommSearchFilter filter) {
    List<Chatroom> chatrooms = filter.isAllSearch() ? repository.findAll() : repository.findAll();
    return chatrooms.stream()
            .map(ChatRoomDto::new)
            .collect(Collectors.toList());
  }

  public Long saveChatroom(PostChatDto dto) {
    List<ChatroomHobby> requestHobbyList = new ArrayList<>();

    Chatroom requestEntity =
        new Chatroom(dto.title(), dto.maxParticipant(), dto.minParticipant(), requestHobbyList);
    Chatroom savedEntity = repository.save(requestEntity);

    // Cache 추가
    cacheParticipantRepository.saveRoom(savedEntity.getId());
    return savedEntity.getId();
  }
  // TODO: chatroom Hobby 적용
}
