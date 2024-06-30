package com.project.lovlind.domain.participaint.repository;

import com.project.lovlind.domain.participaint.entity.Participant;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ParticipantRepository extends JpaRepository<Participant, Long> {

  @Query("select p from Participant p where p.member.id = :memberId and p.chatroom.id = :roomId")
  Optional<Participant> findByMemberIdAndRoomId(
      @Param("memberId") Long memberId, @Param("roomId") Long roomId);

  @Query("select p from Participant p where p.member.id = :memberId")
  List<Participant> findByMemberId(@Param("memberId") Long memberId);

  @Query("delete from Participant p where p.member.id =: memberId")
  void deleteByMemberId(@Param("memberId") Long memberId);
}
