package com.project.lovlind.domain.participaint;

import com.project.lovlind.domain.chat.entity.Chatroom;
import com.project.lovlind.domain.member.entity.Member;
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
public class Participaint {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(updatable = false, name = "participaint_id")
  private Long id;

  @OneToOne
  @JoinColumn(name = "member_id")
  Member member;

  @OneToOne
  @JoinColumn(name = "chatroom_id")
  Chatroom chatroom;
}
