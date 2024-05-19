package com.project.lovlind.domain.chat.entity;

import com.project.lovlind.domain.auditing.BaseTime;
import com.project.lovlind.domain.member.entity.Member;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
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

  @ManyToOne
  @JoinColumn(name = "participant_id")
  Member member;

  @OneToOne
  @JoinColumn(name = "chatroom_id")
  Chatroom chatroom;

  public Message(String message, Member member, Chatroom chatroom) {
    this.message = message;
    this.member = member;
    this.chatroom = chatroom;
  }
}
