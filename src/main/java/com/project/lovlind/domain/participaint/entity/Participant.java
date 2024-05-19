package com.project.lovlind.domain.participaint.entity;

import static jakarta.persistence.EnumType.*;

import com.project.lovlind.domain.chat.entity.Chatroom;
import com.project.lovlind.domain.member.entity.Member;
import com.project.lovlind.domain.participaint.enums.ParticipantStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Participant {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(updatable = false, name = "participant_id")
  private Long id;

  @Enumerated(STRING)
  private ParticipantStatus participantStatus;

  @ManyToOne
  @JoinColumn(name = "member_id")
  Member member;

  @ManyToOne
  @JoinColumn(name = "chatroom_id")
  Chatroom chatroom;

  private Long lastSendMessage;

  public Participant(Member member, Chatroom chatroom) {
    this.member = member;
    this.chatroom = chatroom;
    this.participantStatus = ParticipantStatus.PARTICIPATING;
  }

  public void addChatroom(Chatroom chatroom) {
    this.chatroom = chatroom;
    chatroom.getParticipantsList().add(this);
  }
}
