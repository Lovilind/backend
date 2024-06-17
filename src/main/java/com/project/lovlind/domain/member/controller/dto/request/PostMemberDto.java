package com.project.lovlind.domain.member.controller.dto.request;

import com.project.lovlind.domain.member.enums.LoginType;
import lombok.Getter;

@Getter
public class PostMemberDto {
  private LoginType loginType = LoginType.O01;
  private String email;
  private String password;
  private String rePassword;
  private String code;
}
