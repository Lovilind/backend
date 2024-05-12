package com.project.lovlind.domain.member.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum UserRole {
  ADMIN("ROLE_ADMIN"),
  CLIENT("ROLE_CLIENT");

  private String role;
}
