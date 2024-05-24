package com.project.lovlind.domain.chat.entity;

import com.project.lovlind.domain.participaint.entity.Participant;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Chatroom {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(updatable = false, name = "chatroom_id")
  private Long id;

  @Column(nullable = false)
  private String title;

  @Column(nullable = false)
  private int maxParticipant;

  @Column(nullable = false)
  private int minParticipant;

  @OneToMany(orphanRemoval = true, mappedBy = "chatroom")
  List<Participant> participantsList = new ArrayList<>();

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "chatroom")
  List<ChatroomHobby> chatroomHobbyList = new ArrayList<>();

  public Chatroom(Long id) {
    this.id = id;
  }

  public Chatroom(
      String title, int maxParticipant, int minParticipant, List<ChatroomHobby> chatroomHobbyList) {
    this.title = title;
    this.maxParticipant = maxParticipant;
    this.minParticipant = minParticipant;
    this.chatroomHobbyList = chatroomHobbyList;
  }
}
