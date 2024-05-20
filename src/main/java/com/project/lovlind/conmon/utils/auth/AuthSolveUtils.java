package com.project.lovlind.conmon.utils.auth;

import com.project.lovlind.conmon.requset.dto.CurrentUser;
import com.project.lovlind.domain.chat.cache.dto.PrincipalDto;

public interface AuthSolveUtils {
  PrincipalDto createPrincipal(String accessToken);

  CurrentUser findCurrentUser(String accessToken);
}
