package com.project.lovlind.domain.chat.cache.dto;

import java.security.Principal;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PrincipalDto implements Principal {
  private Long memberId;
  private String nickname;

  @Override
  public String getName() {
    return this.nickname;
  }
}
