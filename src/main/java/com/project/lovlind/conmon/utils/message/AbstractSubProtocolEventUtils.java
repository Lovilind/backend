package com.project.lovlind.conmon.utils.message;

import com.project.lovlind.domain.chat.cache.dto.PrincipalDto;
import org.springframework.web.socket.messaging.AbstractSubProtocolEvent;

public abstract class AbstractSubProtocolEventUtils {
  public static String getChatroomTypeFromHeader(AbstractSubProtocolEvent event) {
    String destination = (String) event.getMessage().getHeaders().get("simpDestination");
    String[] split = destination.split("/");
    return split[2];
  }

  public static Long getChatroomIdFromHeader(AbstractSubProtocolEvent event) {
    String destination = (String) event.getMessage().getHeaders().get("simpDestination");
    String[] split = destination.split("/");
    return Long.parseLong(split[3]);
  }

  public static String getSessionIdFromHeader(AbstractSubProtocolEvent event) {
    return (String) event.getMessage().getHeaders().get("simpSessionId");
  }

  public static PrincipalDto getPrincipalDtoFromEvent(AbstractSubProtocolEvent event) {
    return (PrincipalDto) event.getUser();
  }
}
