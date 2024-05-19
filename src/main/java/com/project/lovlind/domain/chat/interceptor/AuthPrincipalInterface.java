package com.project.lovlind.domain.chat.interceptor;

import com.project.lovlind.domain.chat.cache.dto.PrincipalDto;

public interface AuthPrincipalInterface {
  PrincipalDto createPrincipal();
}
