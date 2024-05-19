package com.project.lovlind.domain.chat.interceptor;

import com.project.lovlind.domain.chat.cache.dto.PrincipalDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class AuthenticationPreInterceptor implements ChannelInterceptor {
  private final AuthPrincipalInterface authPrincipal;

  @Override
  public Message<?> preSend(Message<?> message, MessageChannel channel) {
    StompHeaderAccessor accessor =
        MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);

    findPrincipalDtoByAuth(accessor);

    log.info("connection!! Sender This is  Interceptor");

    return message;
  }

  public void findPrincipalDtoByAuth(StompHeaderAccessor accessor) {
    if (StompCommand.CONNECT.equals(accessor.getCommand())) {
      PrincipalDto user = authPrincipal.createPrincipal();
      accessor.setUser(user);
    }
  }
}
