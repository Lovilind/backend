package com.project.lovlind.domain.member.service;

import com.project.lovlind.conmon.exception.BusinessLogicException;
import com.project.lovlind.domain.member.controller.dto.request.PostMemberDto;
import com.project.lovlind.domain.member.entity.Member;
import com.project.lovlind.domain.member.exception.MemberExceptionCode;
import com.project.lovlind.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {
  private final MemberRepository repository;
  private final PasswordEncoder encoder;

  public Long save(PostMemberDto dto) {
    repository
        .findByEmail(dto.getEmail())
        .ifPresent(
            p -> {
              throw new BusinessLogicException(MemberExceptionCode.USER_EXIST);
            });

    String encodedPassword = encoder.encode(dto.getPassword());
    Member requestMember = new Member(dto.getEmail(), encodedPassword);
    return repository.save(requestMember).getId();
  }
}
