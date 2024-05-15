package com.project.lovlind.domain.histroy;

import com.project.lovlind.domain.chat.entity.Message;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class ChatHistory {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(updatable = false, name = "chat_history_id")
  private Long id;

  @OneToOne
  @JoinColumn(name = "message_id")
  Message messageId;
}
