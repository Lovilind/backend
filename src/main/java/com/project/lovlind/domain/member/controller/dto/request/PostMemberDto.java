package com.project.lovlind.domain.member.controller.dto.request;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PostMemberDto {
  private String email;
  private String password;
}
