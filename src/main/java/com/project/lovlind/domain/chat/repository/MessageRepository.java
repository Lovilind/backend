package com.project.lovlind.domain.chat.repository;

import com.project.lovlind.domain.chat.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {}
