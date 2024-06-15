package com.project.lovlind;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;

@SpringBootApplication
@EnableScheduling
@EnableWebSecurity
@EnableWebSocketMessageBroker
@EnableJpaAuditing
public class Backend {

  public static void main(String[] args) {
    SpringApplication.run(Backend.class, args);
  }
}
