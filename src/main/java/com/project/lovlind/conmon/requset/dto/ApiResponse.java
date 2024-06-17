package com.project.lovlind.conmon.requset.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ApiResponse<T> {

  private T body;
  private LocalDateTime time;

  public ApiResponse(T body) {
    this.body = body;
    this.time = LocalDateTime.now();
  }
}
