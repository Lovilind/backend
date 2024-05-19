package com.project.lovlind.domain.chat.entity;

import static jakarta.persistence.EnumType.*;

import com.project.lovlind.domain.auditing.BaseTime;
import com.project.lovlind.domain.chat.controller.enums.MessageType;
import com.project.lovlind.domain.member.entity.Member;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Message extends BaseTime {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(updatable = false, name = "massage_id")
  private Long id;

  @Lob private String message;

  @Enumerated(STRING)
  private MessageType messageType;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "member_id")
  Member member;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "chatroom_id")
  Chatroom chatroom;

  public Message(String message, Member member, Chatroom chatroom, MessageType messageType) {
    this.message = message;
    this.member = member;
    this.chatroom = chatroom;
    this.messageType = messageType;
  }
}
