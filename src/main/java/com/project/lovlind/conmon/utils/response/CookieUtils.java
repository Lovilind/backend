package com.project.lovlind.conmon.utils.response;

import com.project.lovlind.conmon.utils.response.properties.CookieProperties;
import jakarta.servlet.http.Cookie;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CookieUtils {
  private final CookieProperties cookieProperties;

  public Cookie createCookie(String key) {
    Cookie cookie = new Cookie(cookieProperties.getCookieName(), key);
    cookie.setHttpOnly(true);
    cookie.setSecure(true);
    cookie.setDomain(cookieProperties.getDomain());
    cookie.setPath(cookieProperties.getAcceptedUrl());
    cookie.setMaxAge(60 * cookieProperties.getLimitTime());
    return cookie;
  }
}
