package com.project.lovlind.domain.chat.entity;

import com.project.lovlind.domain.member.entity.Member;
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
public class ChatroomReport {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(updatable = false, name = "chatroom_report_id")
  private Long id;

  @Lob private String reason;

  @OneToOne
  @JoinColumn(name = "reporter_id")
  Member reporter;

  @OneToOne
  @JoinColumn(name = "reported_id")
  Member reportedId;

  @OneToOne
  @JoinColumn(name = "chatroom_id")
  Chatroom chatroom;
}
