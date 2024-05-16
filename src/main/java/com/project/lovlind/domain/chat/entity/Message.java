package com.project.lovlind.domain.chat.entity;

import com.project.lovlind.domain.auditing.BaseTime;
import com.project.lovlind.domain.participaint.entity.Participant;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
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

  @OneToOne
  @JoinColumn(name = "participant_id")
  Participant participant;

  @OneToOne
  @JoinColumn(name = "chatroom_id")
  Chatroom chatroom;
}
