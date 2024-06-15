package com.project.lovlind.domain.chat.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatroomReportService {
  /*private final MemberRepository memberRepository;
  private final ChatroomRepository chatroomRepository;
  private final ChatroomReportRepository repository;

  public void reportChatroom(ChatroomReportDto dto) {
      String reason = dto.getReason();

      Member reporter = memberRepository.findById(dto.getReporter())
              .orElseThrow(() -> new BusinessLogicException());

      Member reported = memberRepository.findById(dto.getReportedId())
              .orElseThrow(() -> new BusinessLogicException("신고 접수자를 찾을 수 없습니다."));

      Chatroom chatroom = chatroomRepository.findById(dto.getChatroom().getId())
              .orElseThrow(() -> new BusinessLogicException("방을 찾을 수 없습니다."));

      ChatroomReport report = new ChatroomReport(reason, reporter, reported, chatroom);

      repository.save(report);
  }*/
}
