package com.project.lovlind.conmon.security.handler;

import com.project.lovlind.conmon.redis.repository.RedisRepository;
import com.project.lovlind.conmon.utils.response.CookieUtils;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LogoutSuccessCustomHandler implements LogoutSuccessHandler {

  private final RedisRepository repository;
  private final CookieUtils cookieUtils;

  private static void createResponse(HttpServletResponse response) throws IOException {
    response.setStatus(HttpStatus.OK.value());
    response.getWriter().flush();
  }

  @Override
  public void onLogoutSuccess(
      HttpServletRequest request, HttpServletResponse response, Authentication authentication)
      throws IOException {

    deleteTokenToRedis(cookieUtils.deleteCookie(request));

    createResponse(response);
  }

  private void deleteTokenToRedis(Cookie refreshCookie) {
    repository.delete(refreshCookie.getValue());
  }
}
