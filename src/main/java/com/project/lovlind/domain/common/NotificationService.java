package com.project.lovlind.domain.common;

import jakarta.mail.Message;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class NotificationService {

  private static final String FROM_EMAIL = "noreply@lovlind.com";

  public boolean sendEmail(String title, String body, String receiver) {
    try {
      Message message = new MimeMessage(null, null);
      message.setFrom(new InternetAddress(FROM_EMAIL, ""));
      message.setRecipients(MimeMessage.RecipientType.TO, InternetAddress.parse(receiver, false));
      message.setSubject(Optional.ofNullable(title).orElse("[LOVLIND] 안내 메일"));

      message.setText(body);
      Transport.send(message);

    } catch (Exception e) {
      // 이메일 서버 세팅
      return true;
    }

    return true;
  }
}
