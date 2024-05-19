package com.project.lovlind.domain.chat.interceptor;

import com.project.lovlind.domain.chat.cache.dto.PrincipalDto;
import org.springframework.stereotype.Component;

@Component
public class AuthPrincipalInterfaceStubImpl implements AuthPrincipalInterface {

  @Override
  public PrincipalDto createPrincipal() {
    return PrincipalDto.builder().memberId(1L).nickname("memberStubA").build();
  }
}
