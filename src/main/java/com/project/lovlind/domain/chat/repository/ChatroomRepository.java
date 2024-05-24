package com.project.lovlind.domain.chat.repository;

import com.project.lovlind.domain.chat.entity.Chatroom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatroomRepository extends JpaRepository<Chatroom, Long> {}
