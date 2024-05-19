package com.project.lovlind.domain.chat.repository;

import com.project.lovlind.domain.chat.entity.Message;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MessageRepository extends JpaRepository<Message, Long> {

  @Query("select m from Message m join fetch m.member where m.chatroom.id = :roomId")
  Slice<Message> findMessageByRoomId(Pageable pageable, @Param("roomId") Long roomId);

  @Query("select count(m) from Message m where m.chatroom.id = :roomId and m.id >= :lastMessageId")
  Long countUnReadMessage(@Param("roomId") Long roomId, @Param("lastMessageId") Long messageId);
}
