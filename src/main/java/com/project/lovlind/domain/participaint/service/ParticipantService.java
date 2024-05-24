package com.project.lovlind.domain.participaint.service;

import com.project.lovlind.domain.chat.entity.Chatroom;
import com.project.lovlind.domain.member.entity.Member;
import com.project.lovlind.domain.participaint.entity.Participant;
import com.project.lovlind.domain.participaint.repository.ParticipantRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ParticipantService {
  private final ParticipantRepository participantRepository;

  public void saveParticipantIfNotExist(Long memberId, Long roomId) {
    if (findEntityByMemberIdAndRoomId(memberId, roomId).isPresent() == false) {
      participantRepository.save(new Participant(new Member(memberId), new Chatroom(roomId)));
    }
  }

  public Optional<Participant> findEntityByMemberIdAndRoomId(Long memberId, Long roomId) {
    return participantRepository.findByMemberIdAndRoomId(memberId, roomId);
  }
}
